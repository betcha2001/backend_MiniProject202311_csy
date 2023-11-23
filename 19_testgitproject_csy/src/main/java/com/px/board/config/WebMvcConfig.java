package com.px.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 환경설정에 관련된 걸 처리하는 객체
public class WebMvcConfig implements WebMvcConfigurer {
	
	// 구현된 interceptor 객체를 등록함
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(new LoginInterceptor())
				.order(1) // 우선순위 설정
				.addPathPatterns("/**") // 전체 요청에 대해 적용
				.excludePathPatterns("/error", "/board/**", "/", 
									 "/user/**", "/css/**", "/js/**", "/img/**"); // 제외시킴


		
	}

}




