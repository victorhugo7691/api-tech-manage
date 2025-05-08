package com.api.tech.manage.postgredb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.tech.manage.postgredb.entity.User;

public interface IUserRepository extends JpaRepository<User, Long> {

}
