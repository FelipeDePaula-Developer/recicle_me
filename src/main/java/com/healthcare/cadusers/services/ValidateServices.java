package com.healthcare.cadusers.services;

import com.healthcare.cadusers.entities.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.regex.Pattern;

@Component
public class ValidateServices {

    public boolean checkEmail(String email){
        String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return Pattern.matches(regexEmail, email);
    }

    public boolean checkCPF(String cpf) {
        cpf = cpf.replaceAll("\\D+", "");
        String regexCPF = "^\\d{11}$";
        return Pattern.matches(regexCPF, cpf);
    }

    public boolean checkPassword(String password){
        String regexPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return Pattern.matches(regexPassword, password);
    }

    public boolean checkUsername(String login) {
        String regexLogin = "^[a-zA-Z0-9_-]{3,20}$";
        return Pattern.matches(regexLogin, login);
    }

    public boolean checkDDI(String phoneDDI) {
        String regexDDI = "^(?:\\d{1,4}\\s?)?\\(?\\d{1,4}\\)?\\s?\\d{6,}$";
        return Pattern.matches(regexDDI, phoneDDI);
    }

    public boolean checkDDD(String phoneDDD) {
        String regexDDD = "^(1[1-9]|[2-9][0-9])$";
        return Pattern.matches(regexDDD, phoneDDD);
    }

    public boolean checkNumber(String phoneNumber) {
        String regexNumber = "^[0-9]{1,20}$";
        return Pattern.matches(regexNumber, phoneNumber);
    }

    public boolean checkTypeNumber(String typeNumber){
        ArrayList<String> types = new ArrayList<>();
        types.add("RE");
        types.add("CO");
        types.add("CE");
        types.add("CN");
        return types.contains(typeNumber);
    }
}
