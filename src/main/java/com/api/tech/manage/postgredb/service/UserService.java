package com.api.tech.manage.postgredb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.tech.manage.exception.DadosNaoEncontradosException;
import com.api.tech.manage.exception.FalhaNaAtividadeException;
import com.api.tech.manage.postgredb.dto.input.UserInputDTO;
import com.api.tech.manage.postgredb.dto.output.UserOutputDTO;
import com.api.tech.manage.postgredb.entity.User;
import com.api.tech.manage.postgredb.mapper.UserMapper;
import com.api.tech.manage.postgredb.repository.IUserRepository;
import com.api.tech.manage.postgredb.service.interfaces.IUserService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserService implements IUserService {

	private IUserRepository userRepository;

	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserOutputDTO saveUser(UserInputDTO userDto) {
		User user = UserMapper.toEntity(userDto);

		try {
			user = this.userRepository.save(user);
		} catch (FalhaNaAtividadeException ex) {
			throw new FalhaNaAtividadeException("Falha ao cadastrar o usuário!");
		}

		return UserMapper.toOutputDTO(user);
	}

	@Override
	public List<UserOutputDTO> findAll() {
		List<User> users = this.userRepository.findAll();

		if (users.isEmpty()) {
			throw new DadosNaoEncontradosException("Não há usuários cadastrados na base de dados.");
		}

		return users.stream().map(UserMapper::toOutputDTO).toList();
	}

	@Override
	public UserOutputDTO findUserById(Long id) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new DadosNaoEncontradosException("Usuário não cadastrado."));
		return UserMapper.toOutputDTO(user);
	}

}
