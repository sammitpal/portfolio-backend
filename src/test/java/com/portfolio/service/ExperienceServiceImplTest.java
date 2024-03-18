package com.portfolio.service;

import com.portfolio.exception.SessionExpiredException;
import com.portfolio.model.Experience;
import com.portfolio.repository.ExperienceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExperienceServiceImplTest {

    @Mock
    private ExperienceRepository experienceRepository;

    @Mock
    private SessionServiceImpl sessionService;

    @InjectMocks
    private ExperienceServiceImpl experienceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateExperience_ValidSession() {
        // Given
        String sessionToken = "validToken";
        Experience experience = new Experience();
        experience.setCompany("Test Experience");

        when(sessionService.verifyToken(sessionToken)).thenReturn(true);
        when(experienceRepository.save(experience)).thenReturn(experience);

        // When
        Experience savedExperience = experienceService.createExperience(experience, sessionToken);

        // Then
        assertNotNull(savedExperience);
        assertEquals(experience.getCompany(), savedExperience.getCompany());
        verify(sessionService, times(1)).verifyToken(sessionToken);
        verify(experienceRepository, times(1)).save(experience);
    }

    @Test
    void testCreateExperience_InvalidSession() {
        // Given
        String sessionToken = "invalidToken";
        Experience experience = new Experience();
        experience.setCompany("Test Experience");

        when(sessionService.verifyToken(sessionToken)).thenReturn(false);

        // When, Then
        assertThrows(SessionExpiredException.class, () -> {
            experienceService.createExperience(experience, sessionToken);
        });

        verify(sessionService, times(1)).verifyToken(sessionToken);
        verify(experienceRepository, never()).save(any(Experience.class));
    }

    @Test
    void testGetAllExperience_ValidSession() {
        // Given
        String sessionToken = "validToken";
        List<Experience> experienceList = new ArrayList<>();
        experienceList.add(new Experience());
        experienceList.add(new Experience());

        when(sessionService.verifyToken(sessionToken)).thenReturn(true);
        when(experienceRepository.findAll()).thenReturn(experienceList);

        // When
        List<Experience> result = experienceService.getAllExperience(sessionToken);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(sessionService, times(1)).verifyToken(sessionToken);
        verify(experienceRepository, times(1)).findAll();
    }

    @Test
    void testGetAllExperience_InvalidSession() {
        // Given
        String sessionToken = "invalidToken";

        when(sessionService.verifyToken(sessionToken)).thenReturn(false);

        // When
        List<Experience> result = experienceService.getAllExperience(sessionToken);

        // Then
        assertNull(result);
        verify(sessionService, times(1)).verifyToken(sessionToken);
        verify(experienceRepository, never()).findAll();
    }
}
