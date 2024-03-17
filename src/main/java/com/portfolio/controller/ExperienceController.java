package com.portfolio.controller;

import com.portfolio.model.Experience;
import com.portfolio.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @PostMapping("/create")
    public ResponseEntity<Experience> createExperience(@Valid @RequestBody Experience experience, @RequestHeader("sessionToken") String sessionToken){
        return new ResponseEntity<>(experienceService.createExperience(experience,sessionToken),HttpStatus.ACCEPTED);
    }
}
