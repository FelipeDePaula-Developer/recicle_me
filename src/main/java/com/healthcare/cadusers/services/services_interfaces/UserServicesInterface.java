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
import java.util.Map;

@Component
public interface UserServicesInterface extends ValidateServices {

    public default Map<String, String> validateUser(User user){
        boolean emailCheck = checkEmail(user.getEmail());
        Map<String, String> ret = new HashMap<>();
        if (!emailCheck)
            ret.put("email", "Email is invalid");

        boolean cpfCheck = checkCPF(user.getCpf());
        if (!cpfCheck)
            ret.put("cpf", "CPF is invalid");

        return ret;
    }

    public default Map<String, String> validateCredential(Credential credential){
        Map<String, String> ret = new HashMap<>();
        boolean usernameCheck = checkUsername(credential.getLogin());
        if (!usernameCheck)
            ret.put("login", "Login is invalid");

        boolean passwordCheck = checkPassword(credential.getPassword());
        if (!passwordCheck)
            ret.put("password", "Password is invalid");

        return ret;
    }

    public default Map<String, String> validatePhone(PhoneNumber phoneNumber){
        boolean ddiCheck = checkDDI(phoneNumber.getPhoneDDI());
        Map<String, String> ret = new HashMap<>();
        if (!ddiCheck)
            ret.put("ddi", "DDI is invalid");

        boolean dddCheck = checkDDD(phoneNumber.getPhoneDDD());
        if (!dddCheck)
            ret.put("ddd", "DDD is invalid");

        boolean numberCheck = checkNumber(phoneNumber.getPhoneNumber());
        if (!numberCheck)
            ret.put("phone_number", "phone number is invalid");

        boolean typeCheck = checkTypeNumber(phoneNumber.getPhoneType());
        if (!typeCheck)
            ret.put("type_number", "phone type is invalid");

        return ret;
    }
    public default void checkValidationsReturn(Map<String, String> returns) throws InvalidDataError{
        if (returns != null && !returns.isEmpty()){
            String errorMsg = returns.entrySet().stream().map(
                    (entry) -> " | " + entry.getKey() + " : " + entry.getValue()).toString();
            throw new  InvalidDataError(errorMsg);
        }
    }
}
