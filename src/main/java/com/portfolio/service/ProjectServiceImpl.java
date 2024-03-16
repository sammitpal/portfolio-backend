package com.portfolio.service;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.exception.SessionExpiredException;
import com.portfolio.model.Project;
import com.portfolio.model.Session;
import com.portfolio.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	Logger LOG = LoggerFactory.getLogger(ProjectServiceImpl.class);

	@Autowired
	IPService ipService;
	
	@Autowired
	ProjectRepository projectRepository;

	@Override
	public Project createProject(Project project, String sessionToken) {
		Project savedProject = null;
		if(verifyToken(sessionToken)) {
			project.setId(UUID.randomUUID().toString());
			savedProject = projectRepository.save(project);
		}
		else {
			throw new SessionExpiredException("Session token is expired or invalid");
		}
		return savedProject; 
	}
	
	@Override
	public List<Project> loadAll(String sessionToken) {
		List<Project> projects;
		if(verifyToken(sessionToken)) {
			LOG.info("TOKEN VERIFICATION SUCCESSFUL");
			projects = projectRepository.findAll();
			LOG.info("FETCH PROJECTS FROM DB");
		}
		else {
			throw new SessionExpiredException("Session token is expired or invalid");
		}
		return projects;
	}

	public Boolean verifyToken(String token) {
		byte[] decode = Base64.getDecoder().decode(token);
		String decodedString = new String(decode);

		String currentIP = ipService.fetchIP().getBody().toString().trim();

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

	@Override
	public String deleteAllProjects() {
		projectRepository.deleteAll();
		return "Deleted";
	}

}
