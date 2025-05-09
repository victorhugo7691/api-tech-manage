package com.api.tech.manage.postgredb.service;

import java.sql.Date;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.tech.manage.exception.FalhaNaAtividadeException;
import com.api.tech.manage.exception.NoContentException;
import com.api.tech.manage.exception.NotFoundException;
import com.api.tech.manage.postgredb.dto.input.UserInputDTO;
import com.api.tech.manage.postgredb.dto.output.UserOutputDTO;
import com.api.tech.manage.postgredb.entity.User;
import com.api.tech.manage.postgredb.enums.EUserType;
import com.api.tech.manage.postgredb.mapper.UserMapper;
import com.api.tech.manage.postgredb.repository.IUserRepository;
import com.api.tech.manage.postgredb.service.interfaces.IUserService;

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
		} catch (DataIntegrityViolationException | FalhaNaAtividadeException ex) {
			throw new FalhaNaAtividadeException("Falha ao cadastrar o usuário!");
		}

		return UserMapper.toOutputDTO(user);
	}

	@Override
	public List<UserOutputDTO> findAll() {
		List<User> users = this.userRepository.findAll();

		if (users.isEmpty()) {
			throw new NoContentException("Não há usuários cadastrados na base de dados.");
		}

		return users.stream().map(UserMapper::toOutputDTO).toList();
	}

	@Override
	public UserOutputDTO findUserById(Long id) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Usuário não cadastrado."));
		return UserMapper.toOutputDTO(user);
	}

	@Override
	public UserOutputDTO updateUser(Long id, UserInputDTO userDto) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Usuário não encontrado."));

		user.setFullName(userDto.getFullName());
		user.setEmail(userDto.getEmail());
		user.setPhone(userDto.getPhone());
		user.setBirthDate(Date.valueOf(userDto.getBirthDate()));
		user.setUserType(EUserType.valueOf(userDto.getUserType()));

		user = this.userRepository.save(user);

		return UserMapper.toOutputDTO(user);
	}

	@Override
	public void deleteUser(Long id) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Usuário com o id: " + id + " não foi encontrado."));

		this.userRepository.delete(user);
	}

}
