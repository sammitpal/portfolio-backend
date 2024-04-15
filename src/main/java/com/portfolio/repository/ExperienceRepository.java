package com.portfolio.repository;

import com.portfolio.model.Experience;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExperienceRepository extends JpaRepository<Experience,String> {
    @Query(value = "SELECT * FROM experiences ORDER BY start_date desc", nativeQuery = true)
	List<Experience> getAllExperiences();
}
