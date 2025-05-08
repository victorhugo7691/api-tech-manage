package com.api.tech.manage.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.tech.manage.postgredb.dto.input.UserInputDTO;
import com.api.tech.manage.postgredb.dto.output.UserOutputDTO;
import com.api.tech.manage.postgredb.service.interfaces.IUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	private IUserService userService;

	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<UserOutputDTO> saveUser(@Valid @RequestBody UserInputDTO userDto) {
		UserOutputDTO user = this.userService.saveUser(userDto);

		if (user == null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(user);
	}

}
