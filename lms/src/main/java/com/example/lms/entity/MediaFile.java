package com.example.lms.entity;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
// import jakarta.persistence.Entity;
import java.io.IOException;

// @Entity
public class MediaFile {
    protected String fileName;
    protected String type;
    protected String description;
    protected Date uploadDate;
    protected  MultipartFile mediaFile;

    public MediaFile(MultipartFile mediaFile){
        this.mediaFile = mediaFile;
    }
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
    public boolean uploadIn(File destination){
        try {
            this.mediaFile.transferTo(destination);
        } catch (IOException ex) {
            return false;
        } 
        return true;
    }
}
