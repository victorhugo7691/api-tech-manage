package com.api.tech.manage.service;

import org.springframework.stereotype.Service;

import com.api.tech.manage.dto.input.UserInputDTO;
import com.api.tech.manage.dto.output.UserOutputDTO;
import com.api.tech.manage.entity.User;
import com.api.tech.manage.exception.FalhaNaAtividadeException;
import com.api.tech.manage.mapper.UserMapper;
import com.api.tech.manage.repository.IUserRepository;
import com.api.tech.manage.service.interfaces.IUserService;

@Service
public class UserService implements IUserService{

	private IUserRepository userRepository;

	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserOutputDTO saveUser(UserInputDTO userDto) {
		User user = UserMapper.toEntity(userDto);
		
		try {
			user =  this.userRepository.save(user);
		} catch(FalhaNaAtividadeException ex) {
			throw new FalhaNaAtividadeException("Falha ao cadastrar o usuário!");
		}
		
		return UserMapper.toOutputDTO(user);
	}

}
