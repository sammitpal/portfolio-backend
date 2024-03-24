package com.portfolio.service;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.exception.ParseException;
import com.portfolio.exception.SessionExpiredException;
import com.portfolio.model.Session;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class SessionServiceImpl {

	@Autowired
	private HttpServletRequest request;

	public String createSession() {
		ObjectMapper obj = new ObjectMapper();
		String jsonString = "";

		Session session = new Session();
		String ip = fetchClientIP().trim(); // Fetching client IP
		session.setIp(ip);
		session.setExpDate(new Date(System.currentTimeMillis() + 60 * 1000 * 30));
		try {
			jsonString = obj.writeValueAsString(session);
		} catch (JsonProcessingException e) {
			throw new ParseException(e.getLocalizedMessage());
		}
		return Base64.getEncoder().encodeToString(jsonString.getBytes());
	}

	private String fetchClientIP() {
		// Retrieve the client's IP address from the X-Forwarded-For header if present
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attributes == null) {
			return ""; // Handle null case
		}

		HttpServletRequest request = attributes.getRequest();
		String ipAddress = request.getHeader("X-Forwarded-For");
		if (ipAddress == null || ipAddress.isEmpty()) {
			ipAddress = request.getHeader("X-Real-IP");
		}
		if (ipAddress == null || ipAddress.isEmpty()) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.isEmpty()) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.isEmpty()) {
			ipAddress = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ipAddress == null || ipAddress.isEmpty()) {
			ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ipAddress == null || ipAddress.isEmpty()) {
			ipAddress = request.getRemoteAddr();
		}

		return ipAddress;
	}

	public Boolean verifyToken(String token) {
		byte[] decode = Base64.getDecoder().decode(token);
		String decodedString = new String(decode);

		Boolean verified = false;

		String currentIP = fetchClientIP().toString().trim();

		ObjectMapper mapper = new ObjectMapper();
		try {
			Date currentDate = new Date();
			Session session = mapper.readValue(decodedString, Session.class);
			if (session != null && session.getExpDate().compareTo(currentDate) > -1 && currentIP.equals(session.getIp())) {
				verified = true;
			}
			return verified;
		} catch (IOException | JsonParseException e) {
			throw new SessionExpiredException(e.getLocalizedMessage());
		}
	}
}
