package com.hanwha.robotics.user.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanwha.robotics.user.common.utils.MailUtil;
import com.hanwha.robotics.user.service.InquiryService;

@Service
public class InquiryServiceImpl implements InquiryService{
    

    @Autowired
    private MailUtil mailUtil;
    
    @Override
    public String sendMail(Map<String,Object> params){
        String result = "FAIL";
        Boolean sendYn = mailUtil.sendMail(params);
        if(sendYn){
            result="OK";
        }
        return result;
    }
}
