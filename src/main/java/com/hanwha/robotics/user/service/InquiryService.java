package com.hanwha.robotics.user.service;

import java.util.Map;

public interface InquiryService {
    
    /*
     * Inquiry >  메일 전송 
     */
    public String sendMail(Map<String,Object> params);
}
