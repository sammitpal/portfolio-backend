package com.portfolio.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.exception.SessionExpiredException;
import com.portfolio.model.Project;
import com.portfolio.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {


	
	@Autowired
	ProjectRepository projectRepository;

	SessionServiceImpl sessionService = null;

	@Override
	public Project createProject(Project project, String sessionToken) {

		sessionService = new SessionServiceImpl();

		Project savedProject = null;
		if(sessionService.verifyToken(sessionToken)==true) {
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
		if(sessionService.verifyToken(sessionToken)==true) {
			projects = projectRepository.getAllProjects();
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
