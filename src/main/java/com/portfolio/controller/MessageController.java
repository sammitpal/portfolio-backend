package com.portfolio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.model.Message;
import com.portfolio.service.MessageService;

@RestController
@RequestMapping("/message")
@CrossOrigin("https://sammit.online")
public class MessageController {
	
	@Autowired
	MessageService messageService;

	@PostMapping("/publish")
	public ResponseEntity<Message> publishMessage(@Valid @RequestBody Message message){
		return new ResponseEntity<>(messageService.createMessage(message),HttpStatus.OK);
	}
	
}
