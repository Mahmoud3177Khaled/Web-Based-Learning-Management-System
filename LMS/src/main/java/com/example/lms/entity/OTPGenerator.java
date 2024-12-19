package com.example.lms.entity;
import java.util.Random;

public class OTPGenerator { 
    public static String generateOTP(){
        String OTPAvailableElements ="0123456789";
        String OTPCode = "";
        Random random = new Random();
        for(int i = 0 ; i < 6; i++){
            OTPCode += OTPAvailableElements.charAt(random.nextInt(10));
        }
        return OTPCode;
    }
}
