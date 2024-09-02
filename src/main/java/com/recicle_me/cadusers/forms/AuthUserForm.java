package com.recicle_me.cadusers.forms;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthUserForm {
    private String login;
    private String password;
}