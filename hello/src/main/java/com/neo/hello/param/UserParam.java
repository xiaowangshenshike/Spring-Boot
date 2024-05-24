package com.neo.hello.param;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

import com.neo.hello.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserParam {
	
	
	private Long id;
	
	@NotEmpty(message = "用户名不能为空")
	private String userName;
	
	@Length(min=6,message="密码不能小于6位")
	private String password;
	
	@Email(message="邮箱格式不合法")
	private String Email;
	
	@Max(value=100,message="年龄不能超过100")
	@Min(value=18,message="年龄必须满足18")
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

	public UserParam() {
		super();
	}

	public UserParam(Long id, @NotEmpty(message = "用户名不能为空") String userName,
			@Length(min = 6, message = "密码不能小于6位") String password,
			@javax.validation.constraints.Email(message = "邮箱格式不合法") String email,
			@Max(value = 100, message = "年龄不能超过100") @Min(value = 18, message = "年龄必须满足18") int age) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		Email = email;
		this.age = age;
	}
	
	
}
