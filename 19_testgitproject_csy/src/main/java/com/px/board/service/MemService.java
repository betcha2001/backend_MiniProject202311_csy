package com.px.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.px.board.command.AddUserCommand;
import com.px.board.command.LoginCommand;
import com.px.board.dtos.MemDto;
import com.px.board.mapper.MemMapper;
import com.px.board.status.RoleStatus;

import jakarta.servlet.http.HttpServletRequest;


@Service
public class MemService {
	
	@Autowired
	private MemMapper memMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public boolean addUser(AddUserCommand addUserCommand) {
		MemDto mdto=new MemDto();
		mdto.setId(addUserCommand.getId());
		mdto.setName(addUserCommand.getName());
		
		//password 암호화하여 저장
		mdto.setPassword(passwordEncoder.encode(addUserCommand.getPassword()));
	
		
		mdto.setEmail(addUserCommand.getEmail());
		mdto.setPhone(addUserCommand.getPhone());
		mdto.setGrade(RoleStatus.USER+"");
		return memMapper.addUser(mdto);
	}
	
	public String idChk(String id) {
		return memMapper.idChk(id);
	}
	
	public String login(LoginCommand loginCommand, HttpServletRequest request, 
			Model model) {
		MemDto dto = memMapper.loginUser(loginCommand.getId());
		String path="cal/calendar";
		if(dto!=null) {
			//로그인 폼에서 입력받은 패스워드값과 DB에 암호화된 패스워드 비교
			if(passwordEncoder.matches(loginCommand.getPassword(), dto.getPassword())) {
				System.out.println("패스워드 같음: 회원이 맞음");
				//session객체에 로그인 정보 저장
				request.getSession().setAttribute("mdto", dto);
				return path;
			}else {
				System.out.println("패스워드 틀림");
				model.addAttribute("msg","패스워드를 확인하세요");
				path="login";
			}
		}else {
			System.out.println("회원이 아닙니다.");
			model.addAttribute("msg","아이디를 확인하세요");
			path="login";
		}
		return path;
	}
}
