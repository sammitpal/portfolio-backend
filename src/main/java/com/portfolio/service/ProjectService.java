package com.portfolio.service;

import java.util.List;

import com.portfolio.model.Project;

public interface ProjectService {

	public Project createProject(Project project, String sessionToken);
	public List<Project> loadAll(String sessionToken);
	public String deleteAllProjects();
	
}
