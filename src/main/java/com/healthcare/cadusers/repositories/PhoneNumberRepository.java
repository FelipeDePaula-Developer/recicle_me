package com.healthcare.cadusers.repositories;

import com.healthcare.cadusers.entities.PhoneNumber;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends CrudRepository<PhoneNumber, Integer> {

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM PhoneNumber p WHERE p.phoneDDI = :ddi AND p.phoneDDD = :ddd AND p.phoneNumber = :number")
    boolean existsByPhoneDDIAndPhoneDDDAndPhoneNumber(String ddi, String ddd, String number);
}
