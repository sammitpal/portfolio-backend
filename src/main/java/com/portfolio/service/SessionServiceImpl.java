package com.portfolio.service;

import java.util.Base64;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.model.Session;

@Service
public class SessionServiceImpl implements SessionService {

	Logger LOG = LoggerFactory.getLogger(SessionService.class);
	
	@Autowired
	IPService ipService;


	@Override
	public String createSession() {
		
		ObjectMapper obj = new ObjectMapper();
		String jsonString="";
		
		Session session = new Session();
		String ip = ipService.fetchIP().getBody().trim();
		session.setIp(ip);
		session.setExpDate(new Date(System.currentTimeMillis()+ 60 * 1000 * 30));
		try {
			jsonString = obj.writeValueAsString(session);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        String encodedString = Base64.getEncoder().encodeToString(jsonString.getBytes());
		return encodedString;
	}
}
