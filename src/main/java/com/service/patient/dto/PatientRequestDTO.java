package com.service.patient.dto;

import com.service.patient.dto.validators.Create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PatientRequestDTO {
	@NotBlank
	@Size(max = 100, message = "size should not exceed 100 character")
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	private String email;

	@NotBlank(message = "Adress cant be null")
	private String address;

	@NotBlank(message = "DOB should not be null")
	private String dateOfBirth;

	@NotBlank(groups = Create.class, message = "Registered Date can't be empty")
	private String registeredDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(String registeredDate) {
		this.registeredDate = registeredDate;
	}

	@Override
	public String toString() {
		return "PatientRequestDTO [name=" + name + ", email=" + email + ", address=" + address + ", dateOfBirth="
				+ dateOfBirth + ", registeredDate=" + registeredDate + "]";
	}

}
