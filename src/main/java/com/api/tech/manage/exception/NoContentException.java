package com.api.tech.manage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Exceção customizada para atender quando não existir conteúdo
@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoContentException(String message) {
		super(message);
	}
}