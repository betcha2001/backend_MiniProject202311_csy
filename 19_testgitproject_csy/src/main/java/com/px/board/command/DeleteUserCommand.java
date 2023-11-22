package com.px.board.command;

public class DeleteUserCommand {
	
	private String id;

	public DeleteUserCommand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeleteUserCommand(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "DeleteUserCommand [id=" + id + "]";
	}
	
	
	
	

}
