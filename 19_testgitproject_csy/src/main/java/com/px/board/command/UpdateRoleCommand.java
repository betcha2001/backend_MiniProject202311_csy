package com.px.board.command;

public class UpdateRoleCommand {

	private String id;
	
	private String name;
	
	private String grade;

	public UpdateRoleCommand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdateRoleCommand(String id, String name, String grade) {
		super();
		this.id = id;
		this.name = name;
		this.grade = grade;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "UpdateRoleCommand [id=" + id + ", name=" + name + ", grade=" + grade + "]";
	}
	
	
}
