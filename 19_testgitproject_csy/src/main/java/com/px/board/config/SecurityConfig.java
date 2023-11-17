package com.px.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 환경설정에 관련된 객체
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filerChain(HttpSecurity http) throws Exception {
//		http.cors().disable().csrf().disable(); // 버전업 --> 사용하지 않음
		
		
//		람다식 문법
//		기능 비활성화
		http.cors(AbstractHttpConfigurer::disable)
			.csrf(AbstractHttpConfigurer::disable) // cors, csrf 비활성화
			.formLogin(AbstractHttpConfigurer::disable); // loginform 비활성화
		
		return http.build();
	}
	
	
	// 패스워드 암호화 객체
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
