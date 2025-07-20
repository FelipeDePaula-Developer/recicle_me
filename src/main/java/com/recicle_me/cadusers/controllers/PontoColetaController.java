package com.recicle_me.cadusers.controllers;

import com.recicle_me.cadusers.dto.DTOLocalPontos;
import com.recicle_me.cadusers.dto.DTOPonto;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "status", "BAD_REQUEST",
                    "messages", pontoColetaFormResult.getAllErrors()
            ));
        } else {
            return ResponseEntity.ok(Map.of(
                    "status", "OK",
                    "message", "Ponto de Coleta Cadastrado"
            ));
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

    @GetMapping("/pontos/list/{tipo}")
    public ResponseEntity<Object> ListPontosColetaByTipo(@PathVariable("tipo") String tipo) {
        List<DTOLocalPontos> dtoLocalPontos = pontoColetaServices.buscarPontosPorTipo(tipo);

        if (dtoLocalPontos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhum ponto de coleta encontrado para o tipo: " + tipo);
        }

        return ResponseEntity.ok(dtoLocalPontos);
    }

    @GetMapping("/pontos/{id}")
    public ResponseEntity<Object> ListPontoById(@PathVariable("id") Integer id) {
        DTOPonto ponto = pontoColetaServices.buscarPorId(id);

        if (ponto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhum ponto de coleta encontrado.");
        }

        return ResponseEntity.ok(ponto);
    }

}
