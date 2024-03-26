package com.portfolio.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.portfolio.service.PdfDocumentService;

@RestController
@RequestMapping("/resume")
public class ResumeController {
	@Autowired
	PdfDocumentService pdfDocumentService;
	
	@PostMapping("/upload")
    public ResponseEntity<String> uploadPdf(@RequestBody MultipartFile file, @RequestHeader(name = "sessionToken") String sessionToken) {
        try {
            pdfDocumentService.savePdf(file,sessionToken);
            return ResponseEntity.status(HttpStatus.OK).body("PDF uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload PDF: " + e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getPdf(@PathVariable String id,@RequestHeader(name = "sessionToken") String sessionToken) {
        byte[] pdfData = pdfDocumentService.getPdfDataById(id,sessionToken);
        if (pdfData != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
