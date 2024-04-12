package com.portfolio.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.exception.SessionExpiredException;
import com.portfolio.model.Project;
import com.portfolio.repository.ProjectRepository;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

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

	@Transactional
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

	@Override
	public Boolean uploadImage(MultipartFile file, String id) throws IOException {
		Boolean uploadstatus = false;
		Project projectFound = projectRepository.findById(id).orElseThrow(()-> new RuntimeException("Error !!"));
        try {
            projectFound.setImage(file.getBytes());
			projectRepository.save(projectFound);
			uploadstatus=true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return uploadstatus;
	}

}
