package com.px.board.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	
	// controller에 진입하기 전에 실행하는 메서드!
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession(); // 세션의 정보 구하기
		if(session.getAttribute("mdto") == null) { // 로그인 X
			System.out.println("로그인 필요");
			response.sendRedirect("/user/login"); // home.html로 이동
		}
		return true;
	}
	
	

}





