package com.recicle_me.cadusers.repositories;

import com.recicle_me.cadusers.entities.PontoColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PontoColetaRepository extends JpaRepository<PontoColeta, Integer> {

}