package com.api.tech.manage.service.interfaces;

import com.api.tech.manage.dto.input.UserInputDTO;
import com.api.tech.manage.dto.output.UserOutputDTO;

public interface IUserService {

	public UserOutputDTO saveUser(UserInputDTO userDto);

}
