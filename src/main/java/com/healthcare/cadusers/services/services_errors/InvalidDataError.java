package com.healthcare.cadusers.services.services_errors;

public class InvalidDataError extends Error{
    public InvalidDataError(String msgError){
        super(msgError);
    }
}
