package com.recicle_me.cadusers.controllers;

import com.recicle_me.cadusers.entities.PontoColeta;
import com.recicle_me.cadusers.entities.TipoColeta;
import com.recicle_me.cadusers.entities.User;
import com.recicle_me.cadusers.forms.PontoColetaForm;
import com.recicle_me.cadusers.forms.TipoColetaForm;
import com.recicle_me.cadusers.forms.results.PontoColetaFormResult;
import com.recicle_me.cadusers.repositories.PontoColetaRepository;
import com.recicle_me.cadusers.repositories.TipoColetaRepository;
import com.recicle_me.cadusers.services.PontoColetaServices;
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
    @Autowired
    private PontoColetaRepository pontoColetaRepository;

    public PontoColetaController(PontoColeta pontoColeta, TipoColeta tipoColeta, PontoColetaServices pontoColetaServices) {
        this.pontoColeta = pontoColeta;
        this.pontoColetaServices = pontoColetaServices;
        this.tipoColeta = tipoColeta;
    }

    @PostMapping("cad/ponto_coleta")
    public ResponseEntity<Object> cadPontoColeta(@RequestBody PontoColetaForm pontoColetaForm) {
        PontoColetaFormResult pontoColetaFormResult = pontoColetaServices.validatePontoColeta(pontoColetaForm);

        if (pontoColetaFormResult.hasErrors()) {
            return new ResponseEntity<>(pontoColetaFormResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Ponto de Coleta Cadastrado", HttpStatus.OK);
        }
    }

    @PostMapping("cad/tipo_coleta")
    public ResponseEntity<Object> cadTipoColeta(@RequestBody TipoColetaForm tipoColetaForm) {

        if (tipoColetaForm.getTipoDescarte().length() > 3) {
            return new ResponseEntity<>("Tipo de descarte invalido", HttpStatus.BAD_REQUEST);
        }

        PontoColeta pontoColeta = pontoColetaRepository.findById(tipoColetaForm.getTipoColetaId())
                .orElseThrow(() -> new RuntimeException("Ponto de coleta não encontrado"));


        TipoColeta tipoColeta = new TipoColeta();
        tipoColeta.setPontoColeta(pontoColeta);
        tipoColeta.setTipoDescarte(tipoColetaForm.getTipoDescarte());

        tipoColetaRepository.save(tipoColeta);
        return new ResponseEntity<>("Tipo de descarte cadastrado", HttpStatus.OK);
    }
}
