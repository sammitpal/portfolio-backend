package com.portfolio.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.model.Experience;
import com.portfolio.service.ExperienceService;

@RestController
@RequestMapping("/experience")
@CrossOrigin("https://sammit.online")
public class ExperienceController {

    @Autowired
    ExperienceService experienceService;
    
    @GetMapping("/getAllExperience")
    public ResponseEntity<List<Experience>> getAllExperience(@RequestHeader("sessionToken") String sessionToken){
        return new ResponseEntity<>(experienceService.getAllExperience(sessionToken), HttpStatus.OK);
    }

    @PostMapping("/experience/create")
    public ResponseEntity<Experience> createExperience(@Valid @RequestBody Experience experience, @RequestHeader("sessionToken") String sessionToken){
        return new ResponseEntity<>(experienceService.createExperience(experience,sessionToken),HttpStatus.ACCEPTED);
    }
}
