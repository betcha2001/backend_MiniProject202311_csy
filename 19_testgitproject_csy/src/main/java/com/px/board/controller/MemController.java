package com.px.board.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.px.board.command.AddUserCommand;
import com.px.board.command.DeleteUserCommand;
import com.px.board.command.LoginCommand;
import com.px.board.command.UpdateCalCommand;
import com.px.board.command.UpdateRoleCommand;
import com.px.board.command.UpdateUserCommand;
import com.px.board.dtos.CalDto;
import com.px.board.dtos.MemDto;
import com.px.board.mapper.MemMapper;
import com.px.board.service.CalServiceImp;
import com.px.board.service.ICalService;
import com.px.board.service.MemService;
import com.px.board.utils.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
public class MemController {

	@Autowired
	private MemService memService;
	@Autowired
	private CalServiceImp calServiceImp;
	@Autowired
	private MemMapper memMapper;
	
	@GetMapping(value = "/addUser")
	public String addUserForm(Model model) {
		System.out.println("회원가입폼 이동");
		
		//회원가입폼에서 addUserCommand객체를 사용하는 코드가 작성되어 있어서
		//null일 경우 오류가 발생하기 때문에 보내줘야 함
		model.addAttribute("addUserCommand", new AddUserCommand());
		
		return "mem/addUserForm";
	}
	
	@PostMapping(value = "/addUser")
	public String addUser(@Validated AddUserCommand addUserCommand,
			BindingResult result, Model model) {
		System.out.println("회원가입하기");
		
		if(result.hasErrors()) {
			System.out.println("회원가입 유효값 오류");
			return "mem/addUserForm";
		}
		
		try {
			memService.addUser(addUserCommand);
			System.out.println("회원가입 성공");
			return "redirect:/";
		} catch (Exception e) {
			System.out.println("회원가입실패");
			e.printStackTrace();
			return "redirect:addUser";
		}
		
	}
	
	@ResponseBody
	@GetMapping(value = "/idChk")
	public Map<String, String> idChk(String id){
		System.out.println("ID중복체크");
		String resultId=memService.idChk(id);
		Map<String, String>map=new HashMap<>();
		map.put("id", resultId);
		return map;
	}
	
	// 로그인 폼 이동
	@GetMapping(value = "/login")
	public String loginForm(Model model) {
		model.addAttribute("loginCommand",new LoginCommand());
		return "login";
	}
	
	// 로그인 실행
	@PostMapping(value = "/login")
	public String login(@Validated LoginCommand loginCommand, 
			BindingResult result, Model model, HttpServletRequest request) {
		if(result.hasErrors()) {
			System.out.println("로그인 유효값 오류");
			return "login";
		}
		
		MemDto dto = memMapper.loginUser(loginCommand.getId());
		// MemDto ldto = memService.login(new MemDto());
		
		
		if(dto == null || dto.getId() == null) {
			model.addAttribute("msg", "회원이 아닙니다. 가입해주세요");
			return "login";
		}else {
			HttpSession session = request.getSession();
			// 회원이면 session 객체에 회원정보를 저장
			session.setAttribute("dto", dto);
			session.setMaxInactiveInterval(10*60);
			
			// 회원 등급에 따라 메인 페이지 이동
			if(dto.getGrade().toUpperCase().equals("ADMIN")) {
				return "cal/calendar_ADMIN";
			}else if(dto.getGrade().toUpperCase().equals("USER")) {
				return "cal/calendar";
			}
		}
		
		String path=memService.login(loginCommand, request, model);
		
		// makeCalendar 가져오기
		Map<String, Integer>map=calServiceImp.makeCalendar(request); 
		model.addAttribute("calMap",map);
		
		// clist 가져오기
//	    List<CalDto> clist = Util.getCalViewList(, clist);
//		model.addAttribute("clist", clist);
		  
	    Calendar cal=Calendar.getInstance();
	    String year=cal.get(Calendar.YEAR)+"";
	    String month=(cal.get(Calendar.MONTH)+1)+"";
	      
	      
	    String yyyyMM=year+Util.isTwo(month); //202311 6자리변환
	    List<CalDto>clist=calServiceImp.calViewList("white", yyyyMM);
	    model.addAttribute("clist",clist);
		
		return path;
	}
	
	// 로그아웃
	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		System.out.println("로그아웃");
		request.getSession().invalidate();
		return "redirect:/";
	}
	
	
	// 나의 정보 조회
	@GetMapping(value="/memInfo")
	public String memInfo(String id, Model model, HttpServletRequest request) {

		System.out.println("memInfo 요청");

//		MemDto dto= memService.getmemInfo(id);
//		HttpSession session = request.getSession();
//		String  = (String) session.getAttribute("id");
		MemDto dto = memService.getmemInfo(id, model, request);	
	    model.addAttribute("dto",dto);
				
		return "mem/meminfo";
		
	}
	
	
	// 나의 정보 수정폼으로 이동
	@GetMapping(value = "/updateMem")
	public String updateMemForm(Model model) {
		System.out.println("나의정보수정폼 이동");
		
		//회원가입폼에서 addUserCommand객체를 사용하는 코드가 작성되어 있어서
		//null일 경우 오류가 발생하기 때문에 보내줘야 함
		model.addAttribute("updateUserCommand", new UpdateUserCommand());
		
		return "mem/updateMemForm";
	}
	
	
	// 나의 정보 수정
	@PostMapping(value="/updateMem")
	public String updateMem(@Validated UpdateUserCommand updateUserCommand,
			BindingResult result, Model model) {

		if(result.hasErrors()) {
			System.out.println("나의 정보 수정 유효값 오류");
			return "mem/updateMemForm";
		}
		
		try {
			memService.updateMem(updateUserCommand);
			System.out.println("나의 정보 수정 성공");
			return "redirect:/";
		} catch (Exception e) {
			System.out.println("나의 정보 수정 실패");
			e.printStackTrace();
			return "redirect:updateMem";
		}		
	}
	
	
	// 회원목록 전체 조회
	@GetMapping(value="/MemAllList")
	public String getAllUserList(Model model) {
		System.out.println("MemAllList 요청");
		
		List<MemDto> mDtoList=memService.getAllMemList();	
		model.addAttribute("list", mDtoList);
		
		return "user/memAllList";
	}
	
	
	// 회원등급수정폼이동
	@GetMapping(value="/memRoleForm")
	public String roleForm(Model model) {
		System.out.println("memRoleForm요청");
		
		return "user/memRoleForm";
	}
	
	
	// 회원등급수정
	@PostMapping(value="/memUpdateRole")
	public String userUpdateRole(@Validated UpdateRoleCommand updateRoleCommand,
			BindingResult result, Model model) {

		System.out.println("memUpdateRole요청");
		
		if(result.hasErrors()) {
			System.out.println("회원등급 수정 유효값 오류");
			return "mem/memUpdateRole";
		}
		
		try {
			memService.memUpdateRole(updateRoleCommand);
			System.out.println("회원등급수정 성공");
			return "redirect:/";
		} catch (Exception e) {
			System.out.println("회원등급수정 실패");
			e.printStackTrace();
			return "redirect:memUpdateRole";
		}
		
	}
	
	// 회원 탈퇴하기
	@PostMapping(value="/delMem")
	public String delMem(@Validated DeleteUserCommand deleteUserCommand,
			BindingResult result, Model model, HttpSession session) {
		
		String id = (String) session.getAttribute("id");
		memService.delMem(id);
		session.invalidate();
		return "login";			
	}
}



