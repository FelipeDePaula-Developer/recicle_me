package com.healthcare.cadusers.services.services_errors;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    private List<String> userErrors = new ArrayList<>();
    private List<String> credentialErrors = new ArrayList<>();
    private List<String> phoneErrors = new ArrayList<>();

    public List<String> getUserErrors() {
        return userErrors;
    }

    public void addUserError(String error) {
        userErrors.add(error);
    }

    public List<String> getCredentialErrors() {
        return credentialErrors;
    }

    public void addCredentialError(String error) {
        credentialErrors.add(error);
    }

    public List<String> getPhoneErrors() {
        return phoneErrors;
    }

    public void addPhoneError(String error) {
        phoneErrors.add(error);
    }

    public boolean hasErrors() {
        return !userErrors.isEmpty() || !credentialErrors.isEmpty() || !phoneErrors.isEmpty();
    }
}
