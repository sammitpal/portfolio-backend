package com.portfolio.service;

import com.portfolio.exception.SessionExpiredException;
import com.portfolio.model.Experience;
import com.portfolio.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExperienceServiceImpl implements ExperienceService{

    @Autowired
    ExperienceRepository experienceRepository;

    SessionServiceImpl sessionService;
    @Override
    public Experience createExperience(Experience experience, String sessionToken) {

        Experience savedExperience = null;

        sessionService = new SessionServiceImpl();
        if(Boolean.TRUE.equals(sessionService.verifyToken(sessionToken))) {
            experience.setId(UUID.randomUUID().toString());
            savedExperience = experienceRepository.save(experience);
        }
        else {
            throw new SessionExpiredException("Session token is expired or invalid");
        }
        return savedExperience;
    }

    @Override
    public List<Experience> getAllExperience(String sessionToken) {
        List<Experience> experiences= null;
        sessionService = new SessionServiceImpl();
        if(Boolean.TRUE.equals(sessionService.verifyToken(sessionToken))){
            experiences = experienceRepository.findAll();
        }
        return experiences;
    }
}
