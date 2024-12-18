package com.example.lms.entity.MediaFiles;

import java.util.Date;
import java.io.File;
// import jakarta.persistence.Entity;

// @Entity
abstract public class MediaFile {
    private String fileName;
    private String type;
    private String description;
    private Date uploadDate;

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
    abstract public boolean uploadIn(File destination);
}
