package com.px.board.mapper;

import com.px.board.dtos.MemDto;

public interface MemMapper {

	public boolean addUser(MemDto dto);
	
	public String idChk(String id);
	
	public MemDto loginUser(String id);
}
