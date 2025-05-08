package com.api.tech.manage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Exceção customizada para atender às falhas de cadastro
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FalhaNaAtividadeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FalhaNaAtividadeException(String message) {
		super(message);
	}

}