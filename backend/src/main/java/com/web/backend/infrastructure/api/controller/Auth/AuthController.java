package com.web.backend.infrastructure.api.controller.Auth;

import com.web.backend.application.DTO.Auth.LoginDto;
import com.web.backend.application.DTO.User.PublicUserDto;
import com.web.backend.application.service.User.UserService;
import com.web.backend.domain.model.user.UserModel;
import com.web.backend.infrastructure.api.utils.auth.JwtTokenUtil;
import com.web.backend.infrastructure.api.utils.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterAuthRequest registerAuthRequest) {
        try {

            UserModel user = new UserModel();

            UserMapper.INSTANCE.RegisterUserToUserModel(registerAuthRequest, user);

            PublicUserDto createUser = userService.createUser(user);

            if (createUser.getUsername().isEmpty()) throw new RuntimeException("Error en la creacion del usuario");

            return ResponseEntity.ok("Usuario registrado correctamente");

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAndTokenGen(@RequestBody LoginDto loginAuthRequest) {

        PublicUserDto user = userService.loginUser(loginAuthRequest);

        if (user.getEmail().isEmpty() || user.getUsername().isEmpty()) throw new RuntimeException("Error en el login");

        String token = jwtTokenUtil.generateToken(loginAuthRequest.getEmail());
        return ResponseEntity.ok(new AuthResponse(token));

    }

    @GetMapping("/test")
    public ResponseEntity<?> testLogin() {
        return ResponseEntity.ok("Token Validado");
    }
}

