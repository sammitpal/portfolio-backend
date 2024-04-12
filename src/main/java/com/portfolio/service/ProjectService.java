package com.portfolio.service;

import java.io.IOException;
import java.util.List;

import com.portfolio.model.Project;
import org.springframework.web.multipart.MultipartFile;

public interface ProjectService {

	public Project createProject(Project project);
	public List<Project> loadAll();
	public String deleteAllProjects();

	public Boolean uploadImage(MultipartFile file,String id) throws IOException;
	
}
