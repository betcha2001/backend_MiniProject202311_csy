package com.px.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.px.board.dtos.MemDto;

@Mapper
public interface MemMapper {

	public boolean addUser(MemDto dto);
	
	public String idChk(String id);
	
	public MemDto loginUser(String id);
	
	public boolean updateMem(MemDto dto);
	
	public boolean memUpdateRole(MemDto dto);
	
	public boolean delMem(String id);
}
