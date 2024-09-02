package com.recicle_me.cadusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CadusersApplication {

    public static void main(String[] args) {
        SpringApplication.run(CadusersApplication.class, args);
    }

}
