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
	ProjectRepository projectRepository;

	SessionServiceImpl sessionService = null;

	@Override
	public Project createProject(Project project, String sessionToken) {

		sessionService = new SessionServiceImpl();

		Project savedProject = null;
		if(sessionService.verifyToken(sessionToken)) {
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

		sessionService = new SessionServiceImpl();

		List<Project> projects;
		if(sessionService.verifyToken(sessionToken)) {
			LOG.info("TOKEN VERIFICATION SUCCESSFUL");
			projects = projectRepository.findAll();
			LOG.info("FETCH PROJECTS FROM DB");
		}
		else {
			throw new SessionExpiredException("Session token is expired or invalid");
		}
		return projects;
	}

	@Override
	public String deleteAllProjects() {
		projectRepository.deleteAll();
		return "Deleted";
	}

}
