package com.tabibi.tabibi_system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tabibi.tabibi_system.Models.Patient;
import com.tabibi.tabibi_system.Models.UserAcc;



public interface PatientRepository extends JpaRepository<Patient,Integer> 
{
    // Patient  findByUserAcc(UserAcc userAcc);

    List<Patient> findByfirstname (String firstname);
    Patient deleteByemail(String email);

    List<Patient> findAllByEmail(String email);
    Patient findByEmail(String email);

    Patient findByUid(long uid);
    
}

