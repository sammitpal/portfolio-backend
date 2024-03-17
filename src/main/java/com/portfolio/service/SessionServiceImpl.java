package com.portfolio.service;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

import com.portfolio.exception.SessionExpiredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.model.Session;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
public class SessionServiceImpl {

	Logger LOG = LoggerFactory.getLogger(SessionServiceImpl.class);

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
			e.printStackTrace();
		}
		String encodedString = Base64.getEncoder().encodeToString(jsonString.getBytes());
		return encodedString;
	}

	private String fetchClientIP() {
		// Retrieve the client's IP address from the X-Forwarded-For header if present
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attributes != null) {
			HttpServletRequest request = attributes.getRequest();
			String ipAddress = request.getHeader("X-Forwarded-For");
			// If X-Forwarded-For header is not present, fall back to remote address
			if (ipAddress == null || ipAddress.isEmpty()) {
				ipAddress = request.getHeader("X-Real-IP");
				if (ipAddress == null || ipAddress.isEmpty()) {
					ipAddress = request.getHeader("Proxy-Client-IP");
					if (ipAddress == null || ipAddress.isEmpty()) {
						ipAddress = request.getHeader("WL-Proxy-Client-IP");
						if (ipAddress == null || ipAddress.isEmpty()) {
							ipAddress = request.getHeader("HTTP_CLIENT_IP");
							if (ipAddress == null || ipAddress.isEmpty()) {
								ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
								if (ipAddress == null || ipAddress.isEmpty()) {
									ipAddress = request.getRemoteAddr();
								}
							}
						}
					}
				}
			}
			return ipAddress;
		} else {
			return ""; // Handle null case
		}
	}


	public Boolean verifyToken(String token) {
		byte[] decode = Base64.getDecoder().decode(token);
		String decodedString = new String(decode);

		String currentIP = fetchClientIP().toString().trim();

		ObjectMapper mapper = new ObjectMapper();
		try {
			Date currentDate = new Date();
			Session session = mapper.readValue(decodedString, Session.class);
			if (session != null) {
				if (session.getExpDate().compareTo(currentDate) > -1) {
					if (currentIP.equals(session.getIp())) {
						LOG.info("grater than -1");
						return true;
					}
					else {
						return false;
					}

				} else {
					LOG.info("less than -1");
					return false;
				}
			} else {
				return false;
			}
		} catch (IOException e) {
			throw new SessionExpiredException(e.getLocalizedMessage());
		} catch (JsonParseException e) {
			throw new SessionExpiredException(e.getLocalizedMessage());
		}
	}
}
