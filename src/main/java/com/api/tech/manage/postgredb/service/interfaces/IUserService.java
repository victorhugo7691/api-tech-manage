package com.api.tech.manage.postgredb.service.interfaces;

import com.api.tech.manage.postgredb.dto.input.UserInputDTO;
import com.api.tech.manage.postgredb.dto.output.UserOutputDTO;

public interface IUserService {

	public UserOutputDTO saveUser(UserInputDTO userDto);

}
