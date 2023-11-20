package com.hanwha.robotics.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanwha.robotics.user.common.dto.ApiResponse;
import com.hanwha.robotics.user.common.enums.ApiStatus;
import com.hanwha.robotics.user.service.InquiryService;
import com.hanwha.robotics.user.service.MainService;

@Controller
public class InquiryController {
    
    @Autowired
    MainService mainService;
    
    @Autowired
    InquiryService inquiryService;


    @RequestMapping(value="/inquiry/normal")
    public String normalInquiryPage(Model model){
        model.addAttribute("inquiryType", "일반문의");
        return "popup/layer_inquiry";
    }

    @RequestMapping(value = "/inquiry/send", method=RequestMethod.POST , consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Object> sendInquiryMail(HttpServletRequest request, HttpServletResponse response , @RequestBody Map<String,Object> params) {
        return new ResponseEntity<>(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), inquiryService.sendMail(params)), HttpStatus.OK);
    }
}
