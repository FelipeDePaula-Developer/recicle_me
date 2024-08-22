package com.healthcare.cadusers.services;

import com.healthcare.cadusers.entities.Client;
import com.healthcare.cadusers.entities.PhoneNumber;
import com.healthcare.cadusers.entities.User;
import com.healthcare.cadusers.entities.interfaces.Person;
import com.healthcare.cadusers.forms.ClientForm;
import com.healthcare.cadusers.forms.UserForm;
import com.healthcare.cadusers.forms.results.PersonFormResult;
import com.healthcare.cadusers.repositories.ClientRepository;
import com.healthcare.cadusers.repositories.PhoneNumberRepository;
import com.healthcare.cadusers.repositories.UserRepository;
import com.healthcare.cadusers.services.services_interfaces.PhoneServicesInterface;
import com.healthcare.cadusers.services.services_interfaces.UserServicesInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class UserServices implements UserServicesInterface, PhoneServicesInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PhoneNumberServices phoneNumberServices;

    @Autowired
    private CredentialServices credentialServices;

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Transactional
    public PersonFormResult registerUser(UserForm userForm) {
        PersonFormResult personFormResult = validateUserForm(userForm);

        if (personFormResult.hasErrors()) {
            return personFormResult;
        }

        User savedUser = saveOrUpdateByCPF(userForm.getUser(), userRepository, cpf -> userRepository.findByCpf(cpf));
        userForm.getPhone().forEach(phone -> phone.setUser(savedUser));
        phoneNumberRepository.saveAll(userForm.getPhone());

        userForm.getCredential().setUser(savedUser);
        credentialServices.saveCredential(userForm.getCredential());

        return personFormResult;
    }

    @Transactional
    public PersonFormResult registerClient(ClientForm clientForm) {
        PersonFormResult personFormResult = validateCommomFields(clientForm.getClient(), clientForm.getPhone());
        if (personFormResult.hasErrors()) {
            return personFormResult;
        }

        Client savedClient = saveOrUpdateByCPF(clientForm.getClient(), clientRepository, cpf -> clientRepository.findByCpf(cpf));
        clientForm.getPhone().forEach(phone -> phone.setClient(savedClient));

        phoneNumberRepository.saveAll(clientForm.getPhone());
        return personFormResult;
    }

    public PersonFormResult validateUserForm(UserForm userForm) {
        PersonFormResult personFormResult = validateCommomFields(userForm.getUser(), userForm.getPhone());

        if (!credentialServices.loginExists(userForm.getCredential())) {
            Map<String, String> retCredential = validateCredential(userForm.getCredential());
            retCredential.forEach((campo, erro) -> personFormResult.addUserError(campo, erro));
        } else {
            personFormResult.addUserError("duplicate_login", "O login " + userForm.getCredential().getLogin() + " já está em uso");
        }

        return personFormResult;
    }

    public PersonFormResult validateCommomFields(Person person, List<PhoneNumber> phone) {
        PersonFormResult personFormResult = new PersonFormResult();

        Map<String, String> retUser = validatePerson(person);
        retUser.forEach((campo, erro) -> personFormResult.addUserError(campo, erro));

        boolean skipPhoneValidation = false;
        for (PhoneNumber phoneNumber : phone) {
            if (phoneNumberServices.phoneNumberExists(phoneNumber)) {
                personFormResult.addUserError(
                        "duplicate_number",
                        "Combinação de DDI (" + phoneNumber.getPhoneDDI() + "), DDD (" + phoneNumber.getPhoneDDD() + ") e Numero " + phoneNumber.getPhoneNumber() + " já existem na base de dados"
                );
                skipPhoneValidation = true;
            }
        }

        if (!skipPhoneValidation) {
            Map<String, String> retPhones = validatePhones(phone);
            retPhones.forEach((campo, erro) -> personFormResult.addUserError(campo, erro));
        }

        return personFormResult;
    }

    public <T extends Person> T saveOrUpdateByCPF(
            T entity,
            CrudRepository<T, Integer> repository,
            java.util.function.Function<String, T> findByCpf
    ) {
        T existingEntity = entity.getCpf() != null ? findByCpf.apply(entity.getCpf()) : null;
        if (existingEntity != null) {
            existingEntity.setName(entity.getName());
            existingEntity.setEmail(entity.getEmail());
            existingEntity.setStatus(entity.getStatus());
            return repository.save(existingEntity);
        } else {
            return repository.save(entity);
        }
    }
}
