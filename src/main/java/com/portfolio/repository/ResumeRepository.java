package com.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.portfolio.model.PdfDocument;

public interface ResumeRepository extends JpaRepository<PdfDocument,String> {
    @Query(value = "SELECT * FROM resumes ORDER BY create_date desc", nativeQuery = true)
    List<PdfDocument> getAlldocument();

}
