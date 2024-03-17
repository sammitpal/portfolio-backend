package com.portfolio.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.model.Project;
import com.portfolio.service.ProjectService;

@RestController
@RequestMapping("/project")
@CrossOrigin("https://sammit.online")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	@PostMapping("/create")
	public ResponseEntity<Project> createProject(@Valid @RequestBody Project project, @RequestHeader(name = "sessionToken") String sessionToken) {
		return new ResponseEntity<>(projectService.createProject(project, sessionToken),HttpStatus.ACCEPTED);
	}
	@GetMapping("/loadAllProjects")
	public ResponseEntity<List<Project>> loadAllProjects(@RequestHeader(name = "sessionToken") String sessionToken) {
		return new ResponseEntity<>(projectService.loadAll(sessionToken),HttpStatus.OK);
	}
}
