package com.healthcare.cadusers.forms;

import com.healthcare.cadusers.entities.Credential;
import com.healthcare.cadusers.entities.PhoneNumber;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserForm {
    private String name;
    private String email;
    private String cpf;
    private List<PhoneNumber> phone;
    private Credential credential;

    public UserForm() {
    }

    public UserForm(String name, String email, String cpf, List<PhoneNumber> phone, Credential credential) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.credential = credential;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<PhoneNumber> getPhone() {
        return phone;
    }

    public void setPhone(List<PhoneNumber> phone) {
        this.phone = phone;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }
}
