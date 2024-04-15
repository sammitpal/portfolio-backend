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

    @Override
    public Experience createExperience(Experience experience) {

        Experience savedExperience = null;

            experience.setId(UUID.randomUUID().toString());
            savedExperience = experienceRepository.save(experience);
        return savedExperience;
    }

    @Override
    public List<Experience> getAllExperience() {
        List<Experience> experiences= null;
            experiences = experienceRepository.getAllExperiences();

        return experiences;
    }
}
