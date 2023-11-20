package com.px.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.px.board.command.LoginCommand;

@Controller
public class LoginController {

Logger logger=LoggerFactory.getLogger(getClass());
	
	@GetMapping(value = "/")
	public String login(Model model) {
		logger.info("LOGIN페이지이동");
		model.addAttribute("loginCommand",new LoginCommand());
		return "login";
	}
}
