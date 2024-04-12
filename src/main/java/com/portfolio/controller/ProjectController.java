package com.portfolio.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.portfolio.model.Project;
import com.portfolio.service.ProjectService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/projects")
@CrossOrigin("https://sammit.online")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	
	@PostMapping("/create")
	public ResponseEntity<Project> createProject(@Valid @RequestBody Project project) {
		return new ResponseEntity<>(projectService.createProject(project),HttpStatus.ACCEPTED);
	}

	@PutMapping("/upload/{id}")
	public ResponseEntity<String> uploadImage(@RequestBody MultipartFile file, @PathVariable String id) throws IOException {
		String message = "An error occured";
		if(projectService.uploadImage(file,id)==true){
			message = "Upload success";
		}
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	@GetMapping("/loadAllProjects")
	public ResponseEntity<List<Project>> loadAllProjects() {
		return new ResponseEntity<>(projectService.loadAll(),HttpStatus.OK);
	}
}
