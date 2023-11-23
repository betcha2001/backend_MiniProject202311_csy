package com.px.board.service;

import java.util.List;
import java.util.Map;

import com.px.board.command.InsertCalCommand;
import com.px.board.command.UpdateCalCommand;
import com.px.board.dtos.CalDto;

import jakarta.servlet.http.HttpServletRequest;

public interface ICalService {

		// 달력생성시 필요한 값 구하는 메서드
		public Map<String, Integer> makeCalendar(HttpServletRequest request);
	
		// 일정 추가
	    public boolean insertCalBoard(InsertCalCommand insertCalCommand)throws Exception;
		// 일정 목록
		public List<CalDto> calBoardList(String id, String yyyyMMdd);
		// 일정 상세조회
		public CalDto calBoardDetail(int seq);
		// 일정 수정하기
		public boolean calBoardUpdate(UpdateCalCommand updateCalCommand);
		// 일정 삭제하기
		public boolean calMulDel(Map<String,String[]>map);
		// 일일의 일정 보여주기
		public List<CalDto> calViewList(String id, String yyyyMM);
		// 하루에 일정이 몇 개 있는지 보여주기
		public int calBoardCount(String id, String yyyyMMdd);
		// 일일의 카테고리,제목 보여주기
		// public List<CalDto> calBoardSummary(String category, String title);
}