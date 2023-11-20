package com.px.board.command;

import java.util.Arrays;

import jakarta.validation.constraints.NotEmpty;

public class DeleteCalCommand {
	
	@NotEmpty(message = "삭제하려면 최소 하나 이상 체크하세요!")
	private String[] number;

	public DeleteCalCommand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeleteCalCommand(@NotEmpty(message = "삭제하려면 최소 하나 이상 체크하세요!") String[] number) {
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
		return "DeleteCalCommand [number=" + Arrays.toString(number) + "]";
	}
	
	

	
	
}





