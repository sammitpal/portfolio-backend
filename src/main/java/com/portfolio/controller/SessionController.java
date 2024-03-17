package com.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.service.SessionService;

@RestController
@RequestMapping("/session")
@CrossOrigin("https://sammit.online")
public class SessionController {

	@Autowired
	SessionService sessionService;

	@GetMapping("/create")
	public String createSession() {
		return sessionService.createSession();
	}
}
