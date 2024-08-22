package com.healthcare.cadusers.repositories;


import com.healthcare.cadusers.entities.Client;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ClientRepository extends CrudRepository<Client, Integer> {

    @Query("SELECT u FROM Client u WHERE u.cpf = :cpf")
    Client findByCpf(String cpf);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM Client u WHERE u.cpf = :cpf")
    boolean existsByCpf(String cpf);
}