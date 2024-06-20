package com.healthcare.cadusers.services.services_errors.exceptions;
import com.healthcare.cadusers.services.services_errors.ValidationResult;

public class ValidationException extends Exception {
    private final ValidationResult validationResult;

    public ValidationException(ValidationResult validationResult) {
        super("Validation errors occurred");
        this.validationResult = validationResult;
    }

    public ValidationResult getValidationResult() {
        return validationResult;
    }
}
