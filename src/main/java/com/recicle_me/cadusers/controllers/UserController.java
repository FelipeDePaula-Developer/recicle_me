package com.recicle_me.cadusers.controllers;

import com.recicle_me.cadusers.forms.AuthUserForm;
import com.recicle_me.cadusers.forms.UserForm;
import com.recicle_me.cadusers.forms.results.PersonFormResult;
import com.recicle_me.cadusers.services.CredentialServices;
import com.recicle_me.cadusers.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;


import java.util.Map;

@RestController
public class UserController {

    private final UserServices userServices;
    private final CredentialServices authServices;

    public UserController(UserServices userServices, CredentialServices authServices) {
        this.userServices = userServices;
        this.authServices = authServices;
    }

    @PostMapping("/user/cad")
    public ResponseEntity<Object> cadUser(@RequestBody UserForm userForm) {
        PersonFormResult personFormResult = userServices.registerUser(userForm);

        if (personFormResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "status", "BAD_REQUEST",
                    "messages", personFormResult.getAllErrors() // deve ser List<Map<String, String>>
            ));
        }

        return ResponseEntity.ok(Map.of(
                "status", "OK",
                "message", "Usuário cadastrado com sucesso"
        ));
    }

    @PostMapping("/user/login")
    public ResponseEntity<Object> authUser(@RequestBody AuthUserForm authUserForm,
                                           HttpServletResponse response) {
        Object token = authServices.authenticate(authUserForm);

        if (token != "") {

            return ResponseEntity.ok(Map.of(
                    "status", "OK",
                    "token", token.toString(),
                    "message", "Login realizado com sucesso"
            ));

            //Vou usar localstorage pela simplicidade
            // Cookie cookie = new Cookie("jwt", token.toString());
            // response.setHeader("Access-Control-Expose-Headers", "Set-Cookie");
            // response.setHeader("Access-Control-Allow-Credentials", "true");
            // cookie.setHttpOnly(true);
            // cookie.setSecure(true);
            // cookie.setPath("/");
            // cookie.setMaxAge(3600);
            // cookie.setAttribute("SameSite", "None");
            //
            // response.addCookie(cookie);
            // return ResponseEntity.ok(Map.of(
            //         "status", "OK",
            //         "message", "Login realizado com sucesso"
            // ));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "status", "UNAUTHORIZED",
                    "message", "Login inválido. Verifique suas credenciais"
            ));
        }
    }

    @PostMapping("/user/logout")
    public void logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setAttribute("SameSite", "None");

        response.addCookie(cookie);
    }
}
