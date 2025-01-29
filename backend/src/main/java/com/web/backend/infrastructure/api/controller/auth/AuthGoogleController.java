package com.web.backend.infrastructure.api.controller.auth;

import com.web.backend.application.DTO.User.PublicUserDto;
import com.web.backend.application.service.User.UserService;
import com.web.backend.config.AppConfig;
import com.web.backend.domain.model.user.UserModel;
import com.web.backend.infrastructure.api.utils.auth.AESUtil;
import com.web.backend.infrastructure.api.utils.auth.CreateCookie;
import com.web.backend.infrastructure.api.utils.auth.LoginType;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/oauth2")
@AllArgsConstructor
public class AuthGoogleController {
    private final AESUtil aesUtil;
    private final UserService userService;
    private final AppConfig appConfig;

    //    Email y name vienen encriptados
    @GetMapping("/success")
    public ResponseEntity<?> success(@RequestParam String token, @RequestParam String email, @RequestParam String name) throws Exception {
        System.out.println("Token recibido en el controlador de callback: " + token);

        String decryptedToken = URLDecoder.decode(aesUtil.decrypt(token), StandardCharsets.UTF_8);
        String decryptedEmail = URLDecoder.decode(aesUtil.decrypt(email), StandardCharsets.UTF_8);
        String decryptedName = URLDecoder.decode(aesUtil.decrypt(name), StandardCharsets.UTF_8);


        UserModel newUser = UserModel.builder()
                .email(decryptedEmail)
                .username(decryptedName)
                .build();

        try {
            PublicUserDto publicUser = userService.loginUser(newUser, LoginType.GOOGLE);
            System.out.println("ANTES COOKIE " + newUser.getEmail() + newUser.getUsername());
            System.out.println(decryptedToken);
            return ResponseEntity.status(302)
                    .header(HttpHeaders.LOCATION, appConfig.getProperty("CLIENT_API"))
                    .header(HttpHeaders.SET_COOKIE, CreateCookie.auth(decryptedToken).toString())
                    .header(HttpHeaders.SET_COOKIE, CreateCookie.cookie("email", email).toString())
                    .header(HttpHeaders.SET_COOKIE, CreateCookie.cookie("name", name).toString())
                    .body(publicUser);
        } catch (RuntimeException e) {

            System.out.println(e.getMessage());
            System.out.println("Registrando Usuario");

            PublicUserDto createUser = userService.createUser(newUser, LoginType.GOOGLE);
            System.out.println(decryptedToken);
            return ResponseEntity.status(302)
                    .header(HttpHeaders.LOCATION, appConfig.getProperty("CLIENT_API"))
                    .header(HttpHeaders.SET_COOKIE, CreateCookie.auth(decryptedToken).toString())
                    .header(HttpHeaders.SET_COOKIE, CreateCookie.cookie("email", email).toString())
                    .header(HttpHeaders.SET_COOKIE, CreateCookie.cookie("name", name).toString())
                    .body(createUser);
        }
    }


}
