package com.recicle_me.cadusers.services;

import com.recicle_me.cadusers.entities.DiasPontoColeta;
import com.recicle_me.cadusers.entities.PontoColeta;
import com.recicle_me.cadusers.entities.TipoColeta;
import com.recicle_me.cadusers.entities.User;
import com.recicle_me.cadusers.forms.PontoColetaForm;
import com.recicle_me.cadusers.forms.results.PontoColetaFormResult;
import com.recicle_me.cadusers.repositories.DiasPontoColetaRepository;
import com.recicle_me.cadusers.repositories.PontoColetaRepository;
import com.recicle_me.cadusers.repositories.TipoColetaRepository;
import com.recicle_me.cadusers.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class PontoColetaServices {

    @Autowired
    PontoColetaRepository pontoColetaRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private DiasPontoColetaRepository diasPontoColetaRepository;

    @Autowired
    private TipoColetaRepository tipoColetaRepository;

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

    public PontoColetaFormResult validatePontoColeta(PontoColetaForm pontoColetaForm) {
        PontoColetaFormResult errors = new PontoColetaFormResult();

        if (!validateCep(pontoColetaForm.getCep())) {
            errors.addLocationError("cep", "CEP inválido");
        }
        if (!validateLogradouro(pontoColetaForm.getLogradouro())) {
            errors.addLocationError("logradouro", "Logradouro inválido");
        }
        if (!validateBairro(pontoColetaForm.getBairro())) {
            errors.addLocationError("bairro", "Bairro inválido");
        }
        if (!validateCidade(pontoColetaForm.getCidade())) {
            errors.addLocationError("cidade", "Cidade inválida");
        }
        if (!validateEstado(pontoColetaForm.getEstado())) {
            errors.addLocationError("estado", "Estado inválido");
        }
        if (!validateStatus(pontoColetaForm.getStatus())) {
            errors.addPontoColetaError("status", "Status inválido");
        }

        User user = userRepository.findById(pontoColetaForm.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        PontoColeta pontoColeta = new PontoColeta();
        pontoColeta.setCep(pontoColetaForm.getCep());
        pontoColeta.setLogradouro(pontoColetaForm.getLogradouro());
        pontoColeta.setBairro(pontoColetaForm.getBairro());
        pontoColeta.setCidade(pontoColetaForm.getCidade());
        pontoColeta.setEstado(pontoColetaForm.getEstado());
        pontoColeta.setStatus(pontoColetaForm.getStatus());
        pontoColeta.setLatitude(pontoColetaForm.getLatitude());
        pontoColeta.setLongitude(pontoColetaForm.getLongitude());
        pontoColeta.setUser(user);
        pontoColetaRepository.save(pontoColeta);

        if (pontoColetaForm.getTipoColeta() != null) {
            TipoColeta tipoColeta = pontoColetaForm.getTipoColeta();
            tipoColeta.setPontoColeta(pontoColeta);
            tipoColetaRepository.save(tipoColeta);
        }

        if (pontoColetaForm.getDiasPontoColeta() != null) {
            pontoColetaForm.getDiasPontoColeta().forEach(dia -> dia.setPontoColeta(pontoColeta));
            diasPontoColetaRepository.saveAll(pontoColetaForm.getDiasPontoColeta());
        }

        return errors;
    }

}
