package com.px.board.service;

import java.util.List;

import com.hk.user.dtos.UserDto;

public interface IUserService {
	
		// 회원가입 
		public boolean insertUser(UserDto dto);
		
		// 아이디 중복 체크
		public String idCheck(String id);
		
		// 로그인
		public UserDto getLogin(UserDto dto);
		
		// 나의 정보 조회 
		public UserDto getUserInfo(String id);
		
		// 나의 정보 수정
		public boolean updateUser(UserDto dto);
		
		// 회원 탈퇴
		public boolean delUser(String id);
		
		// 회원목록 전체조회
		public List<UserDto> getAllUserList();
		
		// 사용중인 회원목록 조회
		public List<UserDto> getUserList();
		
		// 회원등급 수정
		public boolean userUpdateRole(UserDto dto);

}







