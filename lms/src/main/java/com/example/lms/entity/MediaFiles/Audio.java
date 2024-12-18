package com.example.lms.entity.MediaFiles;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class Audio extends MediaFile {
    MultipartFile audioFile;
    public Audio(MultipartFile audioFile) {
        this.audioFile = audioFile;
    }
    @Override
    public boolean uploadIn(File destination) {
        try {
            this.audioFile.transferTo(destination);
        } catch (IOException ex) {
            return false;
        } 
        return true;
    }
}
