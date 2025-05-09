package com.api.tech.manage.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(FalhaNaAtividadeException.class)
	public ResponseEntity<Object> handleNotFoundException(FalhaNaAtividadeException ex, HttpServletRequest request) {
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", "Não foi possível continuar, tente novamente!");
		body.put("message", ex.getMessage());
		body.put("path", request.getRequestURI());

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
			HttpServletRequest request) {
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.CONFLICT.value());
		body.put("error", "Violação de integridade de dados");
		body.put("message", ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage());
		body.put("path", request.getRequestURI());

		return new ResponseEntity<>(body, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(NoContentException.class)
	public ResponseEntity<Object> handleNoContentException(NoContentException ex, HttpServletRequest request) {
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.NO_CONTENT.value());
		body.put("error", "Sem conteúdo");
		body.put("message", ex.getMessage());
		body.put("path", request.getRequestURI());

		return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("error", "Dados não encontrados");
		body.put("message", ex.getMessage());
		body.put("path", request.getRequestURI());

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

}
