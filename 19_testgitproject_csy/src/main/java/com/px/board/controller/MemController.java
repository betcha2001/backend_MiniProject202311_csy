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

import com.hk.user.dtos.UserDto;
import com.px.board.command.AddUserCommand;
import com.px.board.command.LoginCommand;
import com.px.board.dtos.CalDto;
import com.px.board.dtos.MemDto;
import com.px.board.service.CalServiceImp;
import com.px.board.service.ICalService;
import com.px.board.service.MemService;
import com.px.board.utils.Util;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/user")
public class MemController {

	@Autowired
	private MemService memService;
	@Autowired
	private CalServiceImp calServiceImp;
	
	
	@GetMapping(value = "/addUser")
	public String addUserForm(Model model) {
		System.out.println("회원가입폼 이동");
		
		//회원가입폼에서 addUserCommand객체를 사용하는 코드가 작성되어 있어서
		//null일 경우 오류가 발생하기 때문에 보내줘야 함
		model.addAttribute("addUserCommand", new AddUserCommand());
		
		return "mem/addUserForm";
	}
	
	@PostMapping(value = "/addUser")
	public String addUser(@Validated AddUserCommand addUserCommand,BindingResult result, Model model) {
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
		//json객체로 보내기 위해 Map에 담아서 응답
		//text라면 그냥 String으로 보내도 됨
		Map<String, String>map=new HashMap<>();
		map.put("id", resultId);
		return map;
	}
	
	//로그인 폼 이동
	@GetMapping(value = "/login")
	public String loginForm(Model model) {
		model.addAttribute("loginCommand",new LoginCommand());
		return "login";
	}
	
	//로그인 실행
	@PostMapping(value = "/login")
	public String login(@Validated LoginCommand loginCommand, BindingResult result, Model model, HttpServletRequest request) {
		if(result.hasErrors()) {
			System.out.println("로그인 유효값 오류");
			return "login";
		}
		
		String path=memService.login(loginCommand, request, model);
		
		// makeCalendar 가져오기
		Map<String, Integer>map=calServiceImp.makeCalendar(request); 
		model.addAttribute("calMap",map);
		
		//clist 가져오기
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
	
	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		System.out.println("로그아웃");
		request.getSession().invalidate();
		return "redirect:/";
	}
	
	//나의 정보 조회
	@RequestMapping(value="/myinfo.do",method=RequestMethod.GET)
	public String myinfo(String id, Model model) {

		System.out.println("myinfo.do요청");

		MemDto dto= memService.getUserInfo(id);
		model.addAttribute("dto",dto);
		
		return "user/userinfo";
		
	}
	
	//나의 정보 수정
	@RequestMapping(value="/updateUser.do",method=RequestMethod.POST)
	public String updateUser(UserDto dto,Model model) {

		System.out.println("updateUser.do요청");
		
		boolean isS=userService.updateUser(dto);
		
		if(isS) {
			model.addAttribute("msg","수정성공");
			return "redirect:myinfo.do";
		}else {
			model.addAttribute("msg", "수정실패");
			return "user/error";// return "페이지이름"; --> viewResolver가 실행됨			
		}
		
	}
	
	//회원목록 전체 조회
	@RequestMapping(value="/getAllUserList.do",method=RequestMethod.GET)
	public String getAllUserList(Model model) {
		System.out.println("getAllUserList.do요청");
		
		List<UserDto> list=userService.getAllUserList();	
		model.addAttribute("list", list);
		
		return "user/userAllList";// return "페이지이름"; --> viewResolver가 실행됨
	}
	//회원목록 전체 조회[사용중]
	@RequestMapping(value="/getUserList.do",method=RequestMethod.GET)
	public String getUserList(Model model) {
		System.out.println("getUserList.do요청");
		
		List<UserDto> list=userService.getUserList();	
		model.addAttribute("list", list);
		
		return "user/userList";// return "페이지이름"; --> viewResolver가 실행됨
	}
	//회원등급수정폼이동
	@RequestMapping(value="/roleForm.do",method=RequestMethod.GET)
	public String roleForm(Model model) {//파리미터 받는 방법(메서드에 파라미터로 선언)
		System.out.println("roleForm.do요청");
		
		return "user/userRoleForm";
	}
	
	//회원등급수정
	@RequestMapping(value="/userUpdateRole.do",method=RequestMethod.POST)
	public String userUpdateRole(UserDto dto,Model model) {

		System.out.println("userUpdateRole.do요청");
		
		boolean isS=userService.userUpdateRole(dto);
		
		if(isS) {
			model.addAttribute("msg","수정성공");
			return "redirect:roleForm.do";
		}else {
			model.addAttribute("msg", "수정실패");
			return "user/error";// return "페이지이름"; --> viewResolver가 실행됨			
		}
		
	}
}
