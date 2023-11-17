package com.px.board.mapper;

import com.hk.calboard.dtos.MemDto;

public interface MemMapper {

	public boolean addUser(MemDto dto);
	
	public String idChk(String id);
	
	public MemDto loginUser(String id);
}
