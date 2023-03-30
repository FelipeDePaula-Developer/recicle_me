package com.healthcare.cadusers.services;

import com.healthcare.cadusers.entities.Credential;
import com.healthcare.cadusers.entities.PhoneNumber;
import com.healthcare.cadusers.entities.User;
import com.healthcare.cadusers.forms.UserForm;
import com.healthcare.cadusers.repositories.CredentialRepository;
import com.healthcare.cadusers.repositories.PhoneNumberRepository;
import com.healthcare.cadusers.repositories.UserRepository;
import com.healthcare.cadusers.services.services_errors.InvalidDataError;
import com.healthcare.cadusers.services.services_interfaces.UserServicesInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserServices implements UserServicesInterface {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CredentialRepository credentialRepository;
    @Autowired
    PhoneNumberRepository phoneNumberRepository;
    @Transactional(rollbackOn = Error.class)
    public void validateUserForm(UserForm userForm) throws InvalidDataError {
        try {
            User user = new User(userForm.getName(), userForm.getEmail(), userForm.getCpf(), "T");
            Map<String, String> checkUser = validateUser(user);
            Credential credential = userForm.getCredential();
            Map<String, String> checkCredential = validateCredential(credential);
            Map<String, String> ret = new HashMap<>();
            Map<String, String> checkPhones = validatePhones(userForm.getPhone());
            ret.putAll(checkUser);
            ret.putAll(checkCredential);
            ret.putAll(checkPhones);

            checkValidationsReturn(ret);

            userRepository.save(user);
            credentialRepository.save(credential);
            phoneNumberRepository.saveAll(userForm.getPhone());
        }catch (InvalidDataError error){
            throw new InvalidDataError(error.getMessage());
        }
    }
}
