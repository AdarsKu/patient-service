package com.service.patient.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.patient.dto.PatientRequestDTO;
import com.service.patient.dto.PatientResponseDTO;
import com.service.patient.dto.validators.Create;
import com.service.patient.service.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;

@RestController
@RequestMapping
@Tag(name = "Patient", description = "Api for managing patients")
public class PatientController {

	private PatientService patientService;

	public PatientController(PatientService patientService) {
		super();
		this.patientService = patientService;
	}

	@GetMapping("/")
	@Operation(summary = "To get all patients details")
	public ResponseEntity<List<PatientResponseDTO>> getAll() {
		var p = patientService.getPatients();
		return ResponseEntity.ok().body(p);
	}

	@PostMapping("/")
	@Operation(summary = "To add new Patient")
	public ResponseEntity<PatientResponseDTO> addNewPatient(
			@Validated({ Default.class, Create.class }) @RequestBody PatientRequestDTO dto) {
		var pt = patientService.addPatient(dto);
		return ResponseEntity.ok().body(pt);
	}

	@PutMapping("/{id}")
	@Operation(summary = "To Update the Patient details")
	public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id,
			@Validated({ Default.class }) @RequestBody PatientRequestDTO dto) {
		var x = patientService.updatePatient(id, dto);
		return ResponseEntity.ok().body(x);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "To delete Patient from record using id")
	public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
		patientService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
