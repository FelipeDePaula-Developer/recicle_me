package com.healthcare.cadusers.services;

import com.healthcare.cadusers.entities.Credential;
import com.healthcare.cadusers.entities.PhoneNumber;
import com.healthcare.cadusers.entities.User;
import com.healthcare.cadusers.forms.UserForm;
import com.healthcare.cadusers.repositories.CredentialRepository;
import com.healthcare.cadusers.repositories.PhoneNumberRepository;
import com.healthcare.cadusers.repositories.UserRepository;
import com.healthcare.cadusers.services.services_errors.InvalidDataError;
import com.healthcare.cadusers.services.services_errors.ValidationResult;
import com.healthcare.cadusers.services.services_errors.exceptions.ValidationException;
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
    public void validateUserForm(UserForm userForm) throws InvalidDataError, ValidationException {
        ValidationResult validationResult = new ValidationResult();
        try {
            User user = new User(userForm.getName(), userForm.getEmail(), userForm.getCpf(), "T");

            Map<String, String> checkUser = this.validateUser(user);
            checkUser.forEach((field, error) -> validationResult.addUserError(error));

            Credential credential = userForm.getCredential();
            Map<String, String> checkCredential = this.validateCredential(credential);
            checkCredential.forEach((field, error) -> validationResult.addCredentialError(error));

            Map<String, String> checkPhones = this.validatePhones(userForm.getPhone());
            checkPhones.forEach((field, error) -> validationResult.addPhoneError(error));

            if (validationResult.hasErrors()) {
                throw new ValidationException(validationResult);
            }

            userRepository.save(user);
            credentialRepository.save(credential);
            phoneNumberRepository.saveAll(userForm.getPhone());
        } catch (InvalidDataError error) {
            throw new InvalidDataError(error.getMessage());
        }
    }
}
