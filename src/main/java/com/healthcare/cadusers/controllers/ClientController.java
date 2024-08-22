package com.healthcare.cadusers.controllers;

import com.healthcare.cadusers.forms.ClientForm;
import com.healthcare.cadusers.forms.results.PersonFormResult;
import com.healthcare.cadusers.services.CredentialServices;
import com.healthcare.cadusers.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Autowired
    private final UserServices userServices;

    public ClientController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("cad/client")
    public ResponseEntity<Object> cadClient(@RequestBody ClientForm clientForm) {
        PersonFormResult personFormResult = userServices.registerClient(clientForm);

        if (personFormResult.hasErrors()) {
            return new ResponseEntity<>(personFormResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Validation passed", HttpStatus.OK);
        }
    }
}
