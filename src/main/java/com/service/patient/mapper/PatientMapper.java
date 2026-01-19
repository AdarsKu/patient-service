package com.service.patient.mapper;

import java.time.LocalDate;

import com.service.patient.dto.PatientRequestDTO;
import com.service.patient.dto.PatientResponseDTO;
import com.service.patient.model.Patient;


public class PatientMapper {

    public static PatientResponseDTO toDto(Patient entity) {
    	var obj=new PatientResponseDTO();
    			
    	obj.setId(entity.getId().toString());
    	obj.setName(entity.getName());
    	obj.setAddress(entity.getAddress());
    	obj.setDateOfBirth(entity.getDateOfBirth().toString());
    	obj.setRegisteredDate(entity.getRegisteredDate().toString());
    	obj.setEmail(entity.getEmail());
		
    	return obj;
    }

    public static Patient toEntity(PatientRequestDTO dto) {
    	var entity=new Patient();
    	
    	entity.setAddress(dto.getAddress());
    	entity.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
    	entity.setEmail(dto.getEmail());
    	entity.setName(dto.getName());
    	entity.setRegisteredDate(LocalDate.parse(dto.getRegisteredDate()));
    	
    	return entity;
    }
    public static Patient toEntity(PatientRequestDTO dto,Patient entity) {
    	
    	entity.setAddress(dto.getAddress());
    	entity.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
    	entity.setEmail(dto.getEmail());
    	entity.setName(dto.getName());
    	if(dto.getRegisteredDate()!=null)
    	entity.setRegisteredDate(LocalDate.parse(dto.getRegisteredDate()));
    	
    	
    	return entity;
    }
    
    

}