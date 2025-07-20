package com.recicle_me.cadusers.repositories;

import com.recicle_me.cadusers.entities.PontoColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PontoColetaRepository extends JpaRepository<PontoColeta, Integer> {
    @Query("SELECT DISTINCT pc FROM PontoColeta pc " +
            "JOIN TipoColeta tc ON tc.pontoColeta = pc " +
            "WHERE tc.tipoDescarte = :tipo")
    List<PontoColeta> findByTipoDescarte(@Param("tipo") String tipo);


}