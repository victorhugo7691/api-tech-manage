package com.api.tech.manage.postgredb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.api.tech.manage.exception.FalhaNaAtividadeException;
import com.api.tech.manage.exception.NoContentException;
import com.api.tech.manage.exception.NotFoundException;
import com.api.tech.manage.postgredb.dto.input.UserInputDTO;
import com.api.tech.manage.postgredb.dto.output.UserOutputDTO;
import com.api.tech.manage.postgredb.entity.User;
import com.api.tech.manage.postgredb.enums.EUserType;
import com.api.tech.manage.postgredb.mapper.UserMapper;
import com.api.tech.manage.postgredb.repository.IUserRepository;
import com.api.tech.manage.utils.TestFixture;

public class UserServiceTest {

	@Mock
	private IUserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void saveUserSucess() {
		// Arrange
		UserInputDTO inputDTO = TestFixture.createUserInputDto();
		User userEntity = UserMapper.toEntity(inputDTO);

		when(this.userRepository.save(any(User.class))).thenReturn(userEntity);

		// Act
		UserOutputDTO outputDTO = this.userService.saveUser(inputDTO);

		// Assert
		assertNotNull(outputDTO);
		assertEquals("Ana Silva", outputDTO.fullName());
		assertEquals("ADMIN", outputDTO.userType());
		verify(this.userRepository, times(1)).save(any(User.class));
	}

	@Test
	void saveUserFailed() {
		// Arrange
		UserInputDTO inputDTO = TestFixture.createUserInputDto();

		when(this.userRepository.save(any(User.class)))
				.thenThrow(new FalhaNaAtividadeException("Erro ao salvar no banco."));

		// Act
		FalhaNaAtividadeException exception = assertThrows(FalhaNaAtividadeException.class, () -> {
			this.userService.saveUser(inputDTO);
		});

		// Assert
		assertEquals("Falha ao cadastrar o usuário!", exception.getMessage());
		verify(this.userRepository, times(1)).save(any(User.class));
	}

	@Test
	void findAllOk() {
		// Arrange
		List<User> users = Arrays.asList(TestFixture.createUser(), TestFixture.createUser());

		when(this.userRepository.findAll()).thenReturn(users);

		// Act
		List<UserOutputDTO> result = this.userService.findAll();

		// Assert
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("Ana", result.get(0).fullName());
		verify(this.userRepository, times(1)).findAll();
	}

	@Test
	void findAllIsEmpty() {
		// Arrange
		when(this.userRepository.findAll()).thenReturn(Collections.emptyList());

		// Act
		NoContentException exception = assertThrows(NoContentException.class, () -> {
			this.userService.findAll();
		});

		// Assert
		assertEquals("Não há usuários cadastrados na base de dados.", exception.getMessage());
		verify(this.userRepository, times(1)).findAll();
	}

	@Test
	void updateUserSucess() {
		// Arrange
		Long id = 1L;

		UserInputDTO inputDto = TestFixture.createUserInputDto();

		User userAtual = new User();
		userAtual.setId(id);
		userAtual.setFullName("Antigo Nome");
		userAtual.setEmail("antigo@email.com");
		userAtual.setPhone("0000");
		userAtual.setBirthDate(Date.valueOf("1980-01-01"));
		userAtual.setUserType(EUserType.ADMIN);

		when(this.userRepository.findById(id)).thenReturn(Optional.of(userAtual));
		when(this.userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

		// Act
		UserOutputDTO result = this.userService.updateUser(id, inputDto);

		// Assert
		assertNotNull(result);
		assertEquals("Ana Silva", result.fullName());
		assertEquals("ana@gmail.com", result.email());
		verify(this.userRepository, times(1)).save(any(User.class));
	}

	@Test
	void deleteUserSucess() {
		// Arrange
		Long id = 1L;
		User atualUser = new User();
		atualUser.setId(id);

		when(this.userRepository.findById(id)).thenReturn(Optional.of(atualUser));

		// Act
		this.userService.deleteUser(id);

		// Assert
		verify(this.userRepository, times(1)).delete(atualUser);
	}

	@Test
	void deleteUserExcpetion() {
		// Arrange
		Long id = 1L;

		when(this.userRepository.findById(id)).thenReturn(Optional.empty());

		// Act
		assertThrows(NotFoundException.class, () -> this.userService.deleteUser(id));

		// Assert
		verify(this.userRepository, never()).delete(any(User.class));
	}

}
