package com.api.tech.manage.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.api.tech.manage.exception.DadosNaoEncontradosException;
import com.api.tech.manage.postgredb.dto.input.UserInputDTO;
import com.api.tech.manage.postgredb.dto.output.UserOutputDTO;
import com.api.tech.manage.postgredb.service.interfaces.IUserService;
import com.api.tech.manage.utils.TestFixture;

public class UserControllerTest {

	@Mock
	private IUserService userService;

	@InjectMocks
	private UserController userController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void saveUserSucess() {
		// Arrange
		UserInputDTO inputDTO = TestFixture.createUserInputDto();
		UserOutputDTO outputDTO = TestFixture.createUserOutputDto();

		when(this.userService.saveUser(inputDTO)).thenReturn(outputDTO);

		// Act
		ResponseEntity<UserOutputDTO> response = this.userController.saveUser(inputDTO);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("Ana Silva", response.getBody().fullName());
		verify(this.userService, times(1)).saveUser(inputDTO);
	}

	@Test
	void saveUserNoContent() {
		// Arrange
		UserInputDTO inputDTO = TestFixture.createUserInputDto();
		when(this.userService.saveUser(inputDTO)).thenReturn(null);

		// Act
		ResponseEntity<UserOutputDTO> response = this.userController.saveUser(inputDTO);

		// Assert
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		assertNull(response.getBody());
		verify(this.userService, times(1)).saveUser(inputDTO);
	}

	@Test
	void findAll_DeveRetornar200ComListaDeUsuarios() {
		// Arrange
		List<UserOutputDTO> users = Arrays.asList(TestFixture.createUserOutputDto(), TestFixture.createUserOutputDto());

		when(this.userService.findAll()).thenReturn(users);

		// Act
		ResponseEntity<List<UserOutputDTO>> response = this.userController.findAll();

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(2, response.getBody().size());
		verify(this.userService, times(1)).findAll();
	}

	@Test
	void findAll_DeveRetornar204QuandoNaoHouverUsuarios() {
		// Arrange
		when(this.userService.findAll()).thenThrow(new DadosNaoEncontradosException("Não há usuários cadastrados."));

		// Act & Assert
		DadosNaoEncontradosException exception = assertThrows(DadosNaoEncontradosException.class, () -> {
			this.userController.findAll();
		});

		assertEquals("Não há usuários cadastrados.", exception.getMessage());
		verify(this.userService, times(1)).findAll();
	}
}
