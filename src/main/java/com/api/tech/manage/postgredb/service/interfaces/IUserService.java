package com.api.tech.manage.postgredb.service.interfaces;

import java.util.List;

import com.api.tech.manage.postgredb.dto.input.UserInputDTO;
import com.api.tech.manage.postgredb.dto.output.UserOutputDTO;

public interface IUserService {

	public UserOutputDTO saveUser(UserInputDTO userDto);

	public List<UserOutputDTO> findAll();

}
