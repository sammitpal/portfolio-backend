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

	@Override
	public Project createProject(Project project) {

		Project savedProject = null;
			project.setId(UUID.randomUUID().toString());
			savedProject = projectRepository.save(project);
		return savedProject; 
	}
	
	@Override
	public List<Project> loadAll() {

		List<Project> projects;
			projects = projectRepository.getAllProjects();
		return projects;
	}

	@Override
	public String deleteAllProjects() {
		projectRepository.deleteAll();
		return "Deleted";
	}

}
