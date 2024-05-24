package com.neo.hello.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String userName;
	
	@Column
	private String password;
	
	@Column
	private String Email;
	
	@Column
	private int age;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public User(Long id, String userName, String password, String email, int age) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		Email = email;
		this.age = age;
	}

	public User() {
		super();
	}
	
}
