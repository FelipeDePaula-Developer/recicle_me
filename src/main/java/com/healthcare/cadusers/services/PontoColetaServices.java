package com.healthcare.cadusers.services;

import com.healthcare.cadusers.entities.PontoColeta;
import com.healthcare.cadusers.forms.results.PontoColetaFormResult;
import com.healthcare.cadusers.repositories.PontoColetaRepository;
import com.healthcare.cadusers.repositories.TipoColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.regex.Pattern;

@Service
public class PontoColetaServices {

    @Autowired
    PontoColetaRepository pontoColetaRepository;

    public boolean validateCep(String cep) {
        String regexCep = "^[0-9]{5}-?[0-9]{3}$";
        return cep != null && Pattern.matches(regexCep, cep);
    }

    public boolean validateLogradouro(String logradouro) {
        return logradouro != null && !logradouro.trim().isEmpty();
    }

    public boolean validateBairro(String bairro) {
        return bairro != null && !bairro.trim().isEmpty();
    }

    public boolean validateCidade(String cidade) {
        return cidade != null && !cidade.trim().isEmpty();
    }

    public boolean validateEstado(String estado) {
        String regexEstado = "^[A-Z]{2}$";
        return estado != null && Pattern.matches(regexEstado, estado);
    }

    public boolean validateStatus(String status) {
        return status != null && (status.equals("T") || status.equals("F"));
    }

    public PontoColetaFormResult validatePontoColeta(PontoColeta pontoColeta) {
        PontoColetaFormResult errors = new PontoColetaFormResult();

        if (!validateCep(pontoColeta.getCep())) {
            errors.addLocationError("cep", "CEP inválido");
        }
        if (!validateLogradouro(pontoColeta.getLogradouro())) {
            errors.addLocationError("logradouro", "Logradouro inválido");
        }
        if (!validateBairro(pontoColeta.getBairro())) {
            errors.addLocationError("bairro", "Bairro inválido");
        }
        if (!validateCidade(pontoColeta.getCidade())) {
            errors.addLocationError("cidade", "Cidade inválida");
        }
        if (!validateEstado(pontoColeta.getEstado())) {
            errors.addLocationError("estado", "Estado inválido");
        }
        if (!validateStatus(pontoColeta.getStatus())) {
            errors.addPontoColetaError("status", "Status inválido");
        }

        if (!errors.hasErrors()){
            pontoColetaRepository.save(pontoColeta);
        }

        return errors;
    }

}
