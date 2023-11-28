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
	
	   // about
	   @GetMapping(value="/about")
	   public String about() {
		   System.out.println("about 페이지 요청");
		   return "about";
	   }
	
	@GetMapping(value = "/addUser")
	public String addUserForm(Model model) {
		System.out.println("회원가입폼 이동");
		
		// 회원가입폼에서 addUserCommand객체를 사용하는 코드가 작성되어 있어서
		// null일 경우 오류가 발생하기 때문에 보내줘야 함
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
		
		String path=memService.login(loginCommand, request, model);
		
		// makeCalendar 가져오기
		Map<String, Integer>map=calServiceImp.makeCalendar(request); 
		model.addAttribute("calMap",map);
		
		  
	    Calendar cal=Calendar.getInstance();
	    String year=cal.get(Calendar.YEAR)+"";
	    String month=(cal.get(Calendar.MONTH)+1)+"";
	      
	      
	    String yyyyMM=year+Util.isTwo(month); //202311 6자리변환
	    List<CalDto>clist=calServiceImp.calViewList(yyyyMM);
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
	public String memInfo(String id, Model model, HttpServletRequest request, 
			UpdateUserCommand updateUserCommand) {

		System.out.println("memInfo 요청");

		MemDto mdto = memService.getmemInfo(id, model, request);	
	    model.addAttribute("mdto",mdto);
		
		return "mem/meminfo";
		
	}
	
	
	// 나의 정보 수정
	@PostMapping(value="/updateMem")
	public String updateMem(@Validated UpdateUserCommand updateUserCommand,
			BindingResult result, Model model) {

		System.out.println("updateMem 요청");

		if(result.hasErrors()) {
			System.out.println("수정할 목록을 확인하세요");
			return "mem/meminfo";
		}
		
		memService.updateMem(updateUserCommand);
		
		return "redirect:/user/memInfo?id="+updateUserCommand.getId();
	}
	
	
	// 회원목록 전체 조회
	@GetMapping(value="/MemAllList")
	public String MemAllList(Model model) {
		System.out.println("MemAllList 요청");
		
		model.addAttribute("deleteUserCommand", new DeleteUserCommand());
		
		
		List<MemDto> mdto=memService.getAllMemList();	
		model.addAttribute("mdto", mdto);
		
		return "mem/memAllList";
	}
	
	
	// 회원등급수정폼이동
	@GetMapping(value="/memRoleForm")
	public String memRoleForm(Model model) {
		System.out.println("memRoleForm요청");
		
	    model.addAttribute("updateRoleCommand",new UpdateRoleCommand());
		
		return "mem/memRoleForm";
	}
	
	
	// 회원등급수정
	@PostMapping(value="/memUpdateRole")
	public String memUpdateRole(@Validated UpdateRoleCommand updateRoleCommand,
			BindingResult result, Model model) {

		System.out.println("memUpdateRole요청");
		
		if(result.hasErrors()) {
			System.out.println("회원등급 수정 유효값 오류");
			return "mem/memUpdateRole";
		}
		
		try {
			memService.memUpdateRole(updateRoleCommand);
			System.out.println("회원등급수정 성공");
			return "redirect:/memUpdateRole";
		} catch (Exception e) {
			System.out.println("회원등급수정 실패");
			e.printStackTrace();
			return "redirect:memUpdateRole";
		}
		
	}
	
	
	@PostMapping(value = "/delMem")
	   public String delMem(@Validated DeleteUserCommand deleteUserCommand,
	                     BindingResult result,
	                     HttpServletRequest request,
	                     Model model) {
	      
	      if(result.hasErrors()) {
	         System.out.println("최소 하나 이상 체크하기!");
	         
	         HttpSession session = request.getSession();
	         MemDto dto = (MemDto)session.getAttribute("mdto");
	         String id=dto.getId(); //나중에 세션에서 가져온 아이디 사용
	         
	         // session에 저장된 ymd 값은 목록 조회할때 추가되는 코드
	         Map<String, String>map=(Map<String, String>)session.getAttribute("ymdMap");
	         
	         List<MemDto> list= memService.getAllMemList();
	         // 다시 돌아갈 때는 list 객체가 없으니까
	         // 객체를 다시 만들어서 보내줘야 해!
	         model.addAttribute("list", list);
	         return "mem/memAllList";
	      }
	      
	      Map<String,String[]>map=new HashMap<>();
	      map.put("numbers", deleteUserCommand.getNumber());
	      memService.delMem(map);
	      
	      return "redirect:/user/MemAllList";
	   }
	   
	   
	   @GetMapping(value = "/delMem")
	   public String delMem(String[] number) {
	      System.out.println("회원삭제하기");
	      System.out.println(number[0]);
	      Map<String, String[]>map = new HashMap<>();
	      map.put("numbers", number);
	      memService.delMem(map);
	      return "redirect:/user/MemAllList";
	   }
}

