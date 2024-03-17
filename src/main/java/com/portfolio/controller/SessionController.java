package com.portfolio.controller;

import com.portfolio.service.SessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
@CrossOrigin("https://sammit.online")
public class SessionController {

	@Autowired
	SessionServiceImpl sessionService;

	@GetMapping("/create")
	public String createSession() {
		return sessionService.createSession();
	}
}
