package com.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.portfolio.model.Project;

public interface ProjectRepository extends JpaRepository<Project, String>{

    @Query(value = "SELECT * FROM projects ORDER BY start_date desc", nativeQuery = true)
	List<Project> getAllProjects();
	
}
