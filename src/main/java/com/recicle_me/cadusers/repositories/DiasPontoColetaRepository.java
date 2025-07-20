package com.recicle_me.cadusers.repositories;

import com.recicle_me.cadusers.entities.DiasPontoColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DiasPontoColetaRepository extends JpaRepository<DiasPontoColeta, Integer> {
    List<DiasPontoColeta> findByPontoColeta_IdPontoColeta(Integer idPontoColeta);
}