package com.portfolio.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface PdfDocumentService {
    public String savePdf(MultipartFile file) throws IOException;
    public byte[] getPdfData();
}
