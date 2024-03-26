package com.portfolio.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.portfolio.exception.SessionExpiredException;
import com.portfolio.model.PdfDocument;
import com.portfolio.repository.ResumeRepository;

@Service
public class PdfDocumentServiceImpl implements PdfDocumentService{

    @Autowired
    ResumeRepository resumeRepository;
    
    SessionServiceImpl sessionService;
    
    @Override
    public String savePdf(MultipartFile file, String token) throws IOException {
    	sessionService = new SessionServiceImpl();
        PdfDocument pdfDocument = new PdfDocument();
        pdfDocument.setId(UUID.randomUUID().toString());
        pdfDocument.setData(file.getBytes());
        if(Boolean.TRUE.equals(sessionService.verifyToken(token))) {        	
        	resumeRepository.save(pdfDocument);
        	return "Saved";
        }
        else {
        	throw new SessionExpiredException("Session Expired!! Login Again...");
        }
    }
	@Override
	public byte[] getPdfDataById(String id,String token) {
		sessionService = new SessionServiceImpl();
		if(Boolean.TRUE.equals(sessionService.verifyToken(token))) {
			PdfDocument pdfDocument = resumeRepository.findById(id).orElseThrow(()->new RuntimeException("An error occured"));
			return pdfDocument.getData();
		}else {
        	throw new SessionExpiredException("Session Expired!! Login Again...");
		}
	}
}
