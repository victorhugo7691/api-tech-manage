package com.api.tech.manage.mapper;

import com.api.tech.manage.dto.input.UserInputDTO;
import com.api.tech.manage.dto.output.UserOutputDTO;
import com.api.tech.manage.entity.User;
import com.api.tech.manage.enums.EUserType;

public class UserMapper {
	
    public static User toEntity(UserInputDTO dto) {
        User user = new User();
        
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setBirthDate(dto.getBirthDate());
        user.setUserType(EUserType.valueOf(dto.getUserType()));
        
        return user;
    }

    public static UserOutputDTO toOutputDTO(User user) {
        return new UserOutputDTO(
            user.getId(),
            user.getFullName(),
            user.getEmail(),
            user.getPhone(),
            user.getBirthDate(),
            user.getUserType().name()
        );
    }
}

