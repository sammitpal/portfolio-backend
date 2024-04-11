package com.portfolio.service;

import java.util.List;

import com.portfolio.model.Project;

public interface ProjectService {

	public Project createProject(Project project);
	public List<Project> loadAll();
	public String deleteAllProjects();
	
}
