package com.api.tech.manage.postgredb.mapper;

import java.sql.Date;

import com.api.tech.manage.postgredb.dto.input.UserInputDTO;
import com.api.tech.manage.postgredb.dto.output.UserOutputDTO;
import com.api.tech.manage.postgredb.entity.User;
import com.api.tech.manage.postgredb.enums.EUserType;

public class UserMapper {
	
    public static User toEntity(UserInputDTO dto) {
        User user = new User();
        
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setBirthDate(Date.valueOf(dto.getBirthDate()));
        user.setUserType(EUserType.valueOf(dto.getUserType()));
        
        return user;
    }

    public static UserOutputDTO toOutputDTO(User user) {
        return new UserOutputDTO(
            user.getId(),
            user.getFullName(),
            user.getEmail(),
            user.getPhone(),
            user.getBirthDate().toLocalDate(),
            user.getUserType().name()
        );
    }
}

