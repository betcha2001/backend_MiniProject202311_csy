package com.px.board.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.px.board.command.DeleteCalCommand;
import com.px.board.command.InsertCalCommand;
import com.px.board.command.UpdateCalCommand;
import com.px.board.dtos.CalDto;
import com.px.board.service.ICalService;
import com.px.board.utils.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/schedule")
public class CalController {
   // log를 원하는 위치에 설정하여 디버깅
   private static final Logger logger=LoggerFactory.getLogger(CalController.class);
   
   @Autowired
   private ICalService calService;
   
   @GetMapping(value="/calendar")
   public String calendar(Model model, HttpServletRequest request) {
      logger.info("달력보기");
      //달력에서 일일별 일정목록 구하기
      String id="garden"; //나중에 세션에서 가져온 아이디 사용
      
      String year=request.getParameter("year");
      String month=request.getParameter("month");
      
      if(year==null||month==null) {
    	  Calendar cal=Calendar.getInstance();
    	  year=cal.get(Calendar.YEAR)+"";
    	  month=(cal.get(Calendar.MONTH)+1)+"";
      }
      
      String yyyyMM=year+Util.isTwo(month); //202311 6자리변환
      List<CalDto>clist=calService.calViewList(id, yyyyMM);
      model.addAttribute("clist",clist);
      
      //달력만들기위한 값 구하기
      Map<String, Integer>map=calService.makeCalendar(request);
      model.addAttribute("calMap", map);
      
      return "thymeleaf/cal/calendar";
   }
   
   
   @GetMapping(value = "/addCalBoardForm")
   public String addCalBoardForm(Model model, InsertCalCommand insertCalCommand) {
      logger.info("일정추가폼이동");
      System.out.println(insertCalCommand);
      
      //addCalBoardfForm 페이지에서 유효값 처리를 위해 
      // insertCalCommand 받고 있기 때문에 보내줘야 해!
      model.addAttribute("insertCalCommand", insertCalCommand);
      return "thymeleaf/cal/addCalBoardForm";
   }
   
   
   @PostMapping(value = "/addCalBoard")
   public String addCalBoard(@Validated InsertCalCommand insertCalCommand,
                       BindingResult result)throws Exception {
      // 파라미터 순서 꼭 맞춰줄 것!
      logger.info("일정추가하기");
      System.out.println(insertCalCommand);
      
      if(result.hasErrors()) { // 에러가 있으면 돌려보냄
         System.out.println("글을 모두 입력해야 함");
         return "thymeleaf/cal/addCalBoardForm";
      }
      
      // 정상적으로 처리가 된다면
      calService.insertCalBoard(insertCalCommand);
      
      return "redirect:/schedule/calendar?year="+insertCalCommand.getYear()+"&month="+insertCalCommand.getMonth();
   }
   
   
   @GetMapping(value = "/calBoardList")
   public String calBoardList(@RequestParam Map<String, String>map
                     , HttpServletRequest request
                     , Model model) {
      logger.info("일정목록보기");
//      HttpSession session=request.getSession();
//      String id=session.getAttribute("id");
      String id="white"; // 임시로 id 저장
      
      // command 유효값 처리를 위해 기본 생성해서 보내줌
      model.addAttribute("deleteCalCommand", new DeleteCalCommand());
      
      // 일정목록을 조회할때마다 year, month, date를 세션에 저장
      HttpSession session=request.getSession();
      
      if(map.get("year") == null) {
         // 조회한 상태이기때문에 ymd가 저장되어 있어서 값을 가져옴
         map=(Map<String, String>)session.getAttribute("ymdMap");
      }else {
         // 일정을 처음 조회했을때 ymd를 저장함
         session.setAttribute("ymdMap", map);
      }
      
      // 달력에서 전달받은 파라미터 year, month, date를 8자리로 만듦
      String yyyyMMdd=map.get("year")
                   +Util.isTwo(map.get("month"))
                   +Util.isTwo(map.get("date"));
      List<CalDto> list= calService.calBoardList(id, yyyyMMdd);
      model.addAttribute("list", list);
      
      return "thymeleaf/cal/calBoardList";
   }
   
   
   @PostMapping(value = "/calMulDel")
   public String calMulDel(@Validated DeleteCalCommand deleteCalCommand,
                     BindingResult result,
                     HttpServletRequest request,
                     Model model) {
      
      if(result.hasErrors()) {
         System.out.println("최소 하나 이상 체크하기");
         
         HttpSession session=request.getSession();
//         String id=session.getAttribute("id");
         String id="garden"; // 임시로 id 저장
         
         // session에 저장된 ymd 값은 목록 조회할때 추가되는 코드임
         Map<String, String>map=(Map<String, String>)session.getAttribute("ymdMap");
         
         // 달력에서 전달받은 파라미터 year, month, date를 8자리로 만든다.
         String yyyyMMdd=map.get("year")
                      +Util.isTwo(map.get("month"))
                      +Util.isTwo(map.get("date"));
         List<CalDto> list= calService.calBoardList(id, yyyyMMdd);
         model.addAttribute("list", list);
         return "thymeleaf/cal/calBoardList";
      }
      
      Map<String,String[]>map=new HashMap<>();
      map.put("seqs", deleteCalCommand.getNumber());
      calService.calMulDel(map);
      
      return "redirect:/schedule/calBoardList";
   }
   
   @GetMapping("/calMulDel")
   public String calDel(String[] seq) {
	   logger.info("일정삭제하기");
	   System.out.println(seq[0]);
	   Map<String, String[]>map=new HashMap<>();
	   map.put("seqs", seq);
	   calService.calMulDel(map);
	   return "redirect:/schedule/calBoardList";
   }
   
   @GetMapping(value = "/calBoardDetail")
   public String calBoardDetail(UpdateCalCommand updateCalCommand, int number, Model model) {
	   logger.info("일정상세보기");
	   
	   CalDto dto=calService.calBoardDetail(number);
	   
	   //dto -> command
	   updateCalCommand.setNumber(dto.getNumber());
	   updateCalCommand.setId(dto.getId());
	   updateCalCommand.setTitle(dto.getTitle());
	   updateCalCommand.setContent(dto.getContent());
	   updateCalCommand.setYear(Integer.parseInt(dto.getStartdate().substring(0,4)));
	   updateCalCommand.setMonth(Integer.parseInt(dto.getStartdate().substring(4,6)));
	   updateCalCommand.setDate(Integer.parseInt(dto.getStartdate().substring(6,8)));
	   updateCalCommand.setHour(Integer.parseInt(dto.getStartdate().substring(8,10)));
	   updateCalCommand.setMin(Integer.parseInt(dto.getStartdate().substring(10)));
	   
	   updateCalCommand.setYear(Integer.parseInt(dto.getEnddate().substring(0,4)));
	   updateCalCommand.setMonth(Integer.parseInt(dto.getEnddate().substring(4,6)));
	   updateCalCommand.setDate(Integer.parseInt(dto.getEnddate().substring(6,8)));
	   updateCalCommand.setHour(Integer.parseInt(dto.getEnddate().substring(8,10)));
	   updateCalCommand.setMin(Integer.parseInt(dto.getEnddate().substring(10)));
	   
	   model.addAttribute("updateCalCommand",updateCalCommand);
	   
	   return "thymeleaf/cal/calBoardDetail";
   }
   
   @PostMapping(value = "/calBoardUpdate")
   public String calBoardUpdate(@Validated UpdateCalCommand updateCalCommand,BindingResult result,Model model) {
	   logger.info("일정 수정하기");
	   
	   if(result.hasErrors()) {
		   System.out.println("수정할 목록을 확인하세요");
		   return "thymeleaf/cal/calBoardDetail";
	   }
	   
	   calService.calBoardUpdate(updateCalCommand);
	   
	   return "redirect:/schedule/calBoardDetail?seq="+updateCalCommand.getNumber();
   }
   
//   @ResponseBody
//   @GetMapping(value = "/calSummaryAjax")
//   public List<CalDto> calCountAjax(String category, String title){
//	   //logger.info("일정개수");
//	   // Map<String, Integer>map=new HashMap<>();
//	   // String id="aaa";
//	   // calService.calBoardSummary(category, title);
//	   // return dto;
//   }
}