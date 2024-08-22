package com.healthcare.cadusers.forms;
import com.healthcare.cadusers.entities.Client;
import com.healthcare.cadusers.entities.PhoneNumber;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClientForm {

    private Client client = new Client();
    private List<PhoneNumber> phone = new ArrayList<>();
}