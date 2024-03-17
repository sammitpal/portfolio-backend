package com.portfolio.service;

import com.portfolio.model.Experience;

import java.util.List;

public interface ExperienceService {
    public Experience createExperience(Experience experience,String sessionToken);
    public List<Experience> getAllExperience(String sessionToken);
}
