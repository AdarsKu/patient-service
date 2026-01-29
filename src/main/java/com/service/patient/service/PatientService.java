package com.service.patient.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.service.patient.dto.PatientRequestDTO;
import com.service.patient.dto.PatientResponseDTO;
import com.service.patient.exception.EmailAlreadyExistException;
import com.service.patient.exception.PatientNotFoundException;
import com.service.patient.mapper.PatientMapper;
import com.service.patient.repo.PatientRepo;

@Service
public class PatientService {
	private PatientRepo patientRepo;

	public PatientService(PatientRepo patientRepo) {
		this.patientRepo = patientRepo;
	}

	// using dto with mapper
	public List<PatientResponseDTO> getPatients() {
		var patients = patientRepo.findAll();
		List<PatientResponseDTO> patientsDto = patients.stream().map(PatientMapper::toDto).toList();
		return patientsDto;
	}

	public PatientResponseDTO addPatient(PatientRequestDTO req) {
		if (patientRepo.existsByEmail(req.getEmail())) {
			throw new EmailAlreadyExistException("A patient with tis email already exist " + req.getEmail());
		}
		var savePat = patientRepo.save(PatientMapper.toEntity(req));
		return PatientMapper.toDto(savePat);
	}

	public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO dto) {
		var patient = patientRepo.findById(id)
				.orElseThrow(() -> new PatientNotFoundException("Patient Not Found on given id= " + id));
		
		if(patientRepo.existsByEmailAndIdNot(dto.getEmail(),id)) {
			throw new EmailAlreadyExistException("A patient with this email already exist " + dto.getEmail());
		}
		  //if everything goes right map the data coming in response to entity
	      patient=PatientMapper.toEntity(dto, patient);
		
	      //saving data into database;
	      patientRepo.save(patient);
	      
	      // returning patient response dto
	     return  PatientMapper.toDto(patient);
	}

	public void delete(UUID id) {
		patientRepo.deleteById(id);
	}

}
