package com.hk.calboard.dtos;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

@Alias(value = "memDto")
public class MemDto {
	private int number;
	private String name;
	private String id;
	private String password;
	private String email;
	private String phone;
	private String grade;
	private String unregister;
	private Date regdate;
	
	public MemDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemDto(int number, String name, String id, String password, String email, String phone, String grade,
			String unregister, Date regdate) {
		super();
		this.number = number;
		this.name = name;
		this.id = id;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.grade = grade;
		this.unregister = unregister;
		this.regdate = regdate;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getUnregister() {
		return unregister;
	}

	public void setUnregister(String unregister) {
		this.unregister = unregister;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "MemDto [number=" + number + ", name=" + name + ", id=" + id + ", password=" + password + ", email="
				+ email + ", phone=" + phone + ", grade=" + grade + ", unregister=" + unregister + ", regdate="
				+ regdate + "]";
	}
	
	

}
