package com.api.tech.manage.service;

import org.springframework.stereotype.Service;

import com.api.tech.manage.dto.UserInputDTO;
import com.api.tech.manage.entity.User;
import com.api.tech.manage.repository.IUserRepository;

@Service
public class UserService {

	private IUserRepository userRepository;

	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User saveUser(UserInputDTO userDto) {
		return this.userRepository.save(null);
	}

}
