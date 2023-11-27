package com.px.board.command;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public class UpdateUserCommand {
	
	
	@NotBlank(message = "이름을 입력하세요")
	private String name;
	
	
	@NotBlank(message = "전화번호를 입력하세요")
	private String phone;
	
	@NotBlank(message = "이메일을 입력하세요")
	private String email;

	public UpdateUserCommand() {
		super();
	}

	public UpdateUserCommand(@NotBlank(message = "이름을 입력하세요") String name,
			@NotBlank(message = "전화번호를 입력하세요") String phone, @NotBlank(message = "이메일을 입력하세요") String email) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UpdateUserCommand [name=" + name + ", phone=" + phone + ", email=" + email + "]";
	}

}



