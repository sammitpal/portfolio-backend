package com.portfolio.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.model.Message;
import com.portfolio.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	MessageRepository messageRepository;

	@Override
	public Message createMessage(Message message) {
		message.setId(UUID.randomUUID().toString());
		message.setCreateDt(new Date());
		return messageRepository.save(message);
	}
	
}
