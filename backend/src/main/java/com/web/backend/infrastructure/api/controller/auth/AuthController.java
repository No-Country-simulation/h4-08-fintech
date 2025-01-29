package com.web.backend.infrastructure.api.controller.auth;

import com.web.backend.application.dto.auth.ResponseMessage;
import com.web.backend.application.dto.User.PublicUserDto;
import com.web.backend.application.service.User.UserService;
import com.web.backend.config.AppConfig;
import com.web.backend.domain.model.user.UserModel;
import com.web.backend.infrastructure.api.utils.auth.AESUtil;
import com.web.backend.infrastructure.api.utils.auth.CreateCookie;
import com.web.backend.infrastructure.api.utils.auth.JwtTokenUtil;
import com.web.backend.infrastructure.api.utils.auth.LoginType;
import com.web.backend.infrastructure.api.utils.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

    private final UserService userService;
    private JwtTokenUtil jwtTokenUtil;
    private final AESUtil aesUtil;
    private final AppConfig appConfig;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterAuthRequest registerAuthRequest) {
        try {

            UserModel user = new UserModel();

            UserMapper.INSTANCE.RegisterUserToUserModel(registerAuthRequest, user);

            PublicUserDto createUser = userService.createUser(user, LoginType.PASSWORD);

            if (createUser.getUsername().isEmpty()) throw new RuntimeException("Error en la creacion del usuario");

            return ResponseEntity.ok("Usuario registrado correctamente");

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAndTokenGen(@RequestBody UserModel loginAuthRequest) throws Exception {

        PublicUserDto user = userService.loginUser(loginAuthRequest,LoginType.PASSWORD);

        String token = jwtTokenUtil.generateToken(loginAuthRequest.getEmail());
        if (user.getEmail().isEmpty() || user.getUsername().isEmpty()) throw new RuntimeException("Error en el login");
        String encryptedEmail = aesUtil.encrypt(user.getEmail());
        String encryptedName = aesUtil.encrypt(user.getUsername());


        return ResponseEntity.status(302)
                .header(HttpHeaders.LOCATION, appConfig.getProperty("CLIENT_API"))
                .header(HttpHeaders.SET_COOKIE, CreateCookie.auth(token).toString())
                .header(HttpHeaders.SET_COOKIE, CreateCookie.cookie("email",encryptedEmail).toString())
                .header(HttpHeaders.SET_COOKIE, CreateCookie.cookie("name",encryptedName).toString())
                .body(user);

    }

    @GetMapping("/check")
    public ResponseEntity<?> testLogin(@RequestParam boolean clearCookie,
                                       @CookieValue(value = "jwt", required = false) String jwtCookie,
                                       @CookieValue(value = "email", required = false) String emailCookie,
                                       @CookieValue(value = "name", required = false) String nameCookie) {

        System.out.println("Checkeando");

        if (clearCookie) {
            ResponseCookie clearJwtCookie = ResponseCookie.from("jwt", "")
                    .path("/")
                    .maxAge(0)
                    .httpOnly(true)
                    .build();

            ResponseCookie clearEmailCookie = ResponseCookie.from("email", "")
                    .path("/")
                    .maxAge(0)
                    .httpOnly(true)
                    .build();

            ResponseCookie clearNameCookie = ResponseCookie.from("name", "")
                    .path("/")
                    .maxAge(0)
                    .httpOnly(true)
                    .build();

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, clearJwtCookie.toString())
                    .header(HttpHeaders.SET_COOKIE, clearEmailCookie.toString())
                    .header(HttpHeaders.SET_COOKIE, clearNameCookie.toString())
                    .body("Cookies eliminadas");
        }

        try {
            String decryptedJwt = jwtCookie != null ? jwtCookie : "No JWT cookie";
            String decryptedEmail = emailCookie != null ? aesUtil.decrypt(emailCookie) : "No email cookie";
            String decryptedName = nameCookie != null ? aesUtil.decrypt(nameCookie) : "No name cookie";

            ResponseMessage response = ResponseMessage.builder()
                    .email(decryptedEmail)
                    .username(decryptedName)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al desencriptar las cookies: " + e.getMessage());
        }
    }
}

