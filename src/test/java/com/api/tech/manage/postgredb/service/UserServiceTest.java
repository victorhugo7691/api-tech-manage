package com.api.tech.manage.postgredb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.api.tech.manage.exception.FalhaNaAtividadeException;
import com.api.tech.manage.postgredb.dto.input.UserInputDTO;
import com.api.tech.manage.postgredb.dto.output.UserOutputDTO;
import com.api.tech.manage.postgredb.entity.User;
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
    void saveUserNoContent() {
        // Arrange
        UserInputDTO inputDTO = TestFixture.createUserInputDto();

        when(this.userRepository.save(any(User.class)))
            .thenThrow(new FalhaNaAtividadeException("Erro ao salvar no banco."));

        // Act & Assert
        FalhaNaAtividadeException exception = assertThrows(FalhaNaAtividadeException.class, () -> {
            this.userService.saveUser(inputDTO);
        });

        assertEquals("Falha ao cadastrar o usuário!", exception.getMessage());
        verify(this.userRepository, times(1)).save(any(User.class));
    }
	
}
