package com.portfolio.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.portfolio.exception.SessionExpiredException;
import com.portfolio.model.PdfDocument;
import com.portfolio.repository.ResumeRepository;

@Service
public class PdfDocumentServiceImpl implements PdfDocumentService{

    @Autowired
    ResumeRepository resumeRepository;
    

    @Override
    public String savePdf(MultipartFile file) throws IOException {
        PdfDocument pdfDocument = new PdfDocument();
        pdfDocument.setId(UUID.randomUUID().toString());
        pdfDocument.setCreateDate(new Date());
        pdfDocument.setData(file.getBytes());
        	resumeRepository.save(pdfDocument);
        	return "Saved";
    }
	@Override
	@Transactional(readOnly = true)
	public byte[] getPdfData() {
			List<PdfDocument> pdfDocuments = resumeRepository.getAlldocument();
			return pdfDocuments.get(0).getData();
	}
}
