package com.healthcare.cadusers.repositories;

import com.healthcare.cadusers.entities.PontoColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PontoColetaRepository extends JpaRepository<PontoColeta, Integer> {

}