package com.recicle_me.cadusers.repositories;

import com.recicle_me.cadusers.entities.TipoColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TipoColetaRepository extends JpaRepository<TipoColeta, Integer> {
    List<TipoColeta> findByPontoColeta_IdPontoColeta(Integer idPontoColeta);
}