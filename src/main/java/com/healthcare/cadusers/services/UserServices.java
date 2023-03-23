package com.healthcare.cadusers.services;

import com.healthcare.cadusers.forms.UserForm;
import com.healthcare.cadusers.repositories.CredentialRepository;
import com.healthcare.cadusers.repositories.PhoneNumberRepository;
import com.healthcare.cadusers.repositories.UserRepository;
import com.healthcare.cadusers.services.services_interfaces.UserServicesInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserServices implements UserServicesInterface {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CredentialRepository credentialRepository;
    @Autowired
    PhoneNumberRepository phoneNumberRepository;
    @Transactional(rollbackOn = Exception.class)
    public void saveUserForm(UserForm userForm){
        Map<String, String> check = validateUserForm(userForm);
        if( check == null || check.isEmpty()){

        }
    }
}
