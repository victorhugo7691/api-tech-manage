package com.api.tech.manage.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    void saveUserSucesso() {
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
}
