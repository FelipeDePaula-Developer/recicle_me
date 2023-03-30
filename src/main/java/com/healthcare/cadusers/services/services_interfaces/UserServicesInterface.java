package com.healthcare.cadusers.services.services_interfaces;

import com.healthcare.cadusers.entities.Credential;
import com.healthcare.cadusers.entities.PhoneNumber;
import com.healthcare.cadusers.entities.User;
import com.healthcare.cadusers.forms.UserForm;
import com.healthcare.cadusers.repositories.UserRepository;
import com.healthcare.cadusers.services.ValidateServices;
import com.healthcare.cadusers.services.services_errors.InvalidDataError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public interface UserServicesInterface extends ValidateServices {

    public default Map<String, String> validateUser(User user) {
        boolean emailCheck = checkEmail(user.getEmail());
        Map<String, String> ret = new HashMap<>();
        if (!emailCheck)
            ret.put("email", "Email "+user.getEmail()+" is invalid");

        boolean cpfCheck = checkCPF(user.getCpf());
        if (!cpfCheck)
            ret.put("cpf", "CPF "+user.getCpf()+" is invalid");

        return ret;
    }

    public default Map<String, String> validateCredential(Credential credential) {
        Map<String, String> ret = new HashMap<>();
        boolean usernameCheck = checkUsername(credential.getLogin());
        if (!usernameCheck)
            ret.put("login", "Login "+credential.getLogin()+" is invalid");

        boolean passwordCheck = checkPassword(credential.getPassword());
        if (!passwordCheck)
            ret.put("password", "Password "+credential.getPassword()+" is invalid");

        return ret;
    }

    public default Map<String, String> validatePhones(List<PhoneNumber> phonesNumbers) {
        Map<String, String> ret = new HashMap<>();
        phonesNumbers.forEach(phoneNumber -> {
            boolean ddiCheck = checkDDI(phoneNumber.getPhoneDDI());
            if (!ddiCheck)
                ret.put("ddi", "DDI " + phoneNumber.getPhoneDDI() +  " is invalid");

            boolean dddCheck = checkDDD(phoneNumber.getPhoneDDD());
            if (!dddCheck)
                ret.put("ddd", "DDD " +phoneNumber.getPhoneDDD()+ " is invalid");

            boolean numberCheck = checkNumber(phoneNumber.getPhoneNumber());
            if (!numberCheck)
                ret.put("phone_number", "phone "+phoneNumber.getPhoneNumber()+" number is invalid");

            boolean typeCheck = checkTypeNumber(phoneNumber.getPhoneType());
            if (!typeCheck)
                ret.put("type_number", "phone "+phoneNumber.getPhoneType()+" type is invalid");
        });

        return ret;
    }

    public default void checkValidationsReturn(Map<String, String> returns) throws InvalidDataError {
        if (returns != null && !returns.isEmpty()) {
            StringBuilder errorMsg = new StringBuilder("{");
            for (String key : returns.keySet()){
                errorMsg.append(key).append(" : ").append(returns.get(key)).append(", ");
            }
            errorMsg.delete(errorMsg.length() - 2, errorMsg.length()).append("}");
            System.out.println(errorMsg.toString());
            throw new InvalidDataError(errorMsg.toString());
        }
    }
}
