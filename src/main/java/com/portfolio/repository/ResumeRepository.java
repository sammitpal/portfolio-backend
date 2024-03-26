package com.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.model.PdfDocument;

public interface ResumeRepository extends JpaRepository<PdfDocument,String> {
}
