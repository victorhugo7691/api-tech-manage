package com.api.tech.manage.postgredb.entity;

import java.sql.Date;

import org.hibernate.annotations.Type;

import com.api.tech.manage.postgredb.enums.EUserType;
import com.api.tech.manage.postgredb.enums.EUserTypeHibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user", schema = "public")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "full_name", nullable = false)
	private String fullName;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "birth_date", nullable = false)
	private Date birthDate;

	@Column(name = "user_type", nullable = false)
	@Type(value = EUserTypeHibernate.class)
	private EUserType userType;

	public User() {
	};

	public User(Long id, String fullName, String email, String phone, Date birthDate, EUserType userType) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.birthDate = birthDate;
		this.userType = userType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public EUserType getUserType() {
		return userType;
	}

	public void setUserType(EUserType userType) {
		this.userType = userType;
	}

}
