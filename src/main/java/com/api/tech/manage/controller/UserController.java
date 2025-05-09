package com.api.tech.manage.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping
	public ResponseEntity<List<UserOutputDTO>> findAll() {
		return ResponseEntity.ok(this.userService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserOutputDTO> findUserById(@PathVariable Long id) {
		return ResponseEntity.ok(this.userService.findUserById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserOutputDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserInputDTO userDto) {
		UserOutputDTO updatedUser = this.userService.updateUser(id, userDto);

		if (updatedUser == null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		this.userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

}
