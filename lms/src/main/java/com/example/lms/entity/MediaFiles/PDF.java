package com.example.lms.entity.MediaFiles;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
public class PDF extends MediaFile{
    MultipartFile pdfFile;
    public PDF(MultipartFile pdfFile) {
        this.pdfFile = pdfFile;
    }
    @Override
    public boolean uploadIn(File destination) {
        try {
            this.pdfFile.transferTo(destination);
        } catch (IOException ex) {
            return false;
        } 
        return true;
    }
}
