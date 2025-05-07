package com.api.tech.manage.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserInputDTO {

	@NotBlank(message = "O nome do usuário deve ser informado!")
	private String fullName;

	@NotBlank(message = "Informe o e-mail do usuário.")
	@Email(message = "E-mail inválido")
	private String email;

	@NotBlank(message = "O telefone deve ser informado.")
	@Pattern(regexp = "^\\+\\d{1,3}\\s\\d{1,3}\\s\\d{4,5}-\\d{4}$", message = "Número de telefone inválido. Ex: +55 11 99999-9999")
	private String phone;

	@NotNull(message = "Informe a data de nascimento.")
	private LocalDate birthDate;

	@NotBlank(message = "O tipo do usuário deve ser informado, considera-se, ADMIN, EDITOR ou VIEWER")
	private String userType;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
