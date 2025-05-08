package com.api.tech.manage.utils;

import java.time.LocalDate;

import com.api.tech.manage.postgredb.dto.input.UserInputDTO;
import com.api.tech.manage.postgredb.dto.output.UserOutputDTO;

public class TestFixture {

	public static UserInputDTO createUserInputDto() {
		UserInputDTO inputDTO = new UserInputDTO();
		inputDTO.setFullName("Ana Silva");
		inputDTO.setEmail("ana@gmail.com");
		inputDTO.setPhone("+55 11 99999-9999");
		inputDTO.setBirthDate(LocalDate.of(1995, 5, 5));
		inputDTO.setUserType("ADMIN");
		return inputDTO;
	}

	public static UserOutputDTO createUserOutputDto() {
		return new UserOutputDTO(1L, "Ana Silva", "ana@gmail.com", "+55 11 99999-9999", LocalDate.of(1995, 05, 05),
				"ADMIN");
	}
	
}
