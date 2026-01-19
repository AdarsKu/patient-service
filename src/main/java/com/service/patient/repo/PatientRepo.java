package com.service.patient.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.patient.model.Patient;

@Repository 
public interface PatientRepo extends JpaRepository<Patient, UUID> {
	
	boolean existsByEmail(String email);
	boolean existsByEmailAndIdNot(String email,UUID id);
}
