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
            return new ResponseEntity<>(personFormResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Validation passed", HttpStatus.OK);
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<Object> authUser(@RequestBody AuthUserForm authUserForm,
                                                 HttpServletResponse response) {
        Object token = authServices.authenticate(authUserForm);

        if (token != null && !Boolean.FALSE.equals(token)) {
            Cookie cookie = new Cookie("jwt", token.toString());

            response.setHeader("Access-Control-Expose-Headers", "Set-Cookie");
            response.setHeader("Access-Control-Allow-Credentials", "true");

            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            cookie.setAttribute("SameSite", "None");

            response.addCookie(cookie);
            return new ResponseEntity<>("Logged", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Login Fail", HttpStatus.BAD_REQUEST);
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
