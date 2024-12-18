package com.example.lms.entity.UserRequest;


import org.springframework.web.multipart.MultipartFile;

public class PDFUploadRequest extends UserRequest {
    MultipartFile pdfFile;

    public MultipartFile getPDFFile() {
        return this.pdfFile;
    }

    public void setPDFFile(MultipartFile pdfFile) {
        this.pdfFile = pdfFile;
    }
}
