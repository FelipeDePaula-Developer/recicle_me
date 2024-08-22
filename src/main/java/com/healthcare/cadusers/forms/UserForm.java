package com.healthcare.cadusers.forms;

import com.healthcare.cadusers.entities.Credential;
import com.healthcare.cadusers.entities.PhoneNumber;
import com.healthcare.cadusers.entities.User;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserForm {

    private User user = new User();
    private List<PhoneNumber> phone = new ArrayList<>();
    private Credential credential = new Credential();
}
