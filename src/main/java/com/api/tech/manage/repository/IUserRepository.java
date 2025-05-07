package com.api.tech.manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.tech.manage.entity.User;

public interface IUserRepository extends JpaRepository<User, Long> {

}
