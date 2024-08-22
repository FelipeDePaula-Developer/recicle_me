package com.healthcare.cadusers.controllers;

import com.healthcare.cadusers.entities.PontoColeta;
import com.healthcare.cadusers.entities.TipoColeta;
import com.healthcare.cadusers.forms.UserForm;
import com.healthcare.cadusers.forms.results.PersonFormResult;
import com.healthcare.cadusers.forms.results.PontoColetaFormResult;
import com.healthcare.cadusers.repositories.TipoColetaRepository;
import com.healthcare.cadusers.services.CredentialServices;
import com.healthcare.cadusers.services.PontoColetaServices;
import com.healthcare.cadusers.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PontoColetaController {


    @Autowired
    TipoColetaRepository tipoColetaRepository;

    public PontoColeta pontoColeta;
    public TipoColeta tipoColeta;
    public PontoColetaServices pontoColetaServices;

    public PontoColetaController(PontoColeta pontoColeta, TipoColeta tipoColeta ,PontoColetaServices pontoColetaServices) {
        this.pontoColeta = pontoColeta;
        this.pontoColetaServices = pontoColetaServices;
        this.tipoColeta = tipoColeta;
    }

    @PostMapping("cad/ponto_coleta")
    public ResponseEntity<Object> cadPontoColeta(@RequestBody PontoColeta pontoColeta) {
        PontoColetaFormResult pontoColetaFormResult = pontoColetaServices.validatePontoColeta(pontoColeta);

        if (pontoColetaFormResult.hasErrors()) {
            return new ResponseEntity<>(pontoColetaFormResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Ponto de Coleta Cadastrado", HttpStatus.OK);
        }
    }
    @PostMapping("cad/tipo_coleta")
    public ResponseEntity<Object> cadTipoColeta(@RequestBody TipoColeta tipoColeta) {
        if (tipoColeta.getPontoColeta() != null && tipoColeta.getTipoDescarte() != null) {
            tipoColetaRepository.save(tipoColeta);
            return new ResponseEntity<>("Tipo de descarte cadastrado", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Erro no cadastro do tipoo de descarte", HttpStatus.BAD_REQUEST);
        }
    }
}
