package com.api.tech.manage.postgredb.dto.output;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public record UserOutputDTO(
		Long id, 
		String fullName, 
		String email, 
		String phone, 
		@JsonFormat(pattern = "dd/MM/yyyy") LocalDate birthDate, 
		String userType) {

}
