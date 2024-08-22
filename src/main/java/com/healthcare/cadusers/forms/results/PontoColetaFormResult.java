package com.healthcare.cadusers.forms.results;

import java.util.ArrayList;
import java.util.List;

public class PontoColetaFormResult {

    private final List<ValidationError> pontoColetaErrors = new ArrayList<>();
    private final List<ValidationError> locationErrors = new ArrayList<>();

    public List<ValidationError> getPontoColetaErrors() {
        return new ArrayList<>(pontoColetaErrors);
    }

    public void addPontoColetaError(String field, String message) {
        pontoColetaErrors.add(new ValidationError(field, message));
    }

    public List<ValidationError> getLocationErrors() {
        return new ArrayList<>(locationErrors);
    }

    public void addLocationError(String field, String message) {
        locationErrors.add(new ValidationError(field, message));
    }

    public boolean hasErrors() {
        return !pontoColetaErrors.isEmpty() || !locationErrors.isEmpty();
    }

    public List<ValidationError> getAllErrors() {
        List<ValidationError> allErrors = new ArrayList<>(pontoColetaErrors);
        allErrors.addAll(locationErrors);
        return allErrors;
    }
}
