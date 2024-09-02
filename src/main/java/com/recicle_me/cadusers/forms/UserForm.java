package com.recicle_me.cadusers.forms;

import com.recicle_me.cadusers.entities.Credential;
import com.recicle_me.cadusers.entities.PhoneNumber;
import com.recicle_me.cadusers.entities.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserForm {
    private User user = new User();
    private List<PhoneNumber> phone = new ArrayList<>();
    private Credential credential = new Credential();
}
