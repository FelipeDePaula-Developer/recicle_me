package com.healthcare.cadusers.controllers;

import com.healthcare.cadusers.forms.UserForm;
import com.healthcare.cadusers.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.net.URISyntaxException;

@RestController
public class CadUsersController {

    @Autowired
    private UserServices userServices;

    @PostMapping("cad/user")
    public ResponseEntity<?> operatorRegistration(@RequestBody UserForm userForm) throws URISyntaxException {
        userServices.saveOperator(userForm);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
