package com.healthcare.cadusers.controllers;

import com.healthcare.cadusers.forms.AuthUserForm;
import com.healthcare.cadusers.forms.UserForm;
import com.healthcare.cadusers.forms.results.PersonFormResult;
import com.healthcare.cadusers.services.CredentialServices;
import com.healthcare.cadusers.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {

    private final UserServices userServices;
    private final CredentialServices authServices;

    public UserController(UserServices userServices, CredentialServices authServices) {
        this.userServices = userServices;
        this.authServices = authServices;
    }

    @PostMapping("cad/user")
    public ResponseEntity<Object> cadUser(@RequestBody UserForm userForm) {
        PersonFormResult personFormResult = userServices.registerUser(userForm);

        if (personFormResult.hasErrors()) {
            return new ResponseEntity<>(personFormResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Validation passed", HttpStatus.OK);
        }
    }

    @PostMapping("auth/user")
    public ResponseEntity<Object> authUser(@RequestBody AuthUserForm authUserForm) {
        if (authServices.authenticate(authUserForm)) {
            return new ResponseEntity<>("Logged", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Login Fail", HttpStatus.BAD_REQUEST);
        }
    }
}
