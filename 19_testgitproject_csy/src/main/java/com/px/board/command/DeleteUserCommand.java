package com.px.board.command;

import java.util.Arrays;

public class DeleteUserCommand {

	private String[] number;
	
	public DeleteUserCommand() {
		super();
	}

	public DeleteUserCommand(String[] number) {
		super();
		this.number = number;
	}

	public String[] getNumber() {
		return number;
	}

	public void setNumber(String[] number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "DeleteUserCommand [number=" + Arrays.toString(number) + "]";
	}

	
	

	

}
