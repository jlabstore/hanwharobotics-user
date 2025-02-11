package com.hanwha.robotics.user.controller;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanwha.robotics.user.common.dto.ApiResponse;
import com.hanwha.robotics.user.common.enums.ApiStatus;
import com.hanwha.robotics.user.service.InquiryService;
import com.hanwha.robotics.user.service.MainService;

import org.springframework.beans.factory.annotation.Value;

@Controller
@RequestMapping("/contact")
public class InquiryController {

    @Autowired
    MainService mainService;

    @Autowired
    InquiryService inquiryService;

    /**
     * 문의 유형 별 메일 주소 설정
     */
    @Value("${mail.address.product}")
    private String productMail;

    @Value("${mail.address.distributor}")
    private String distributorMail;

    @Value("${mail.address.cooperation}")
    private String cooperationMail;

    @Value("${mail.address.education}")
    private String educationMail;


    /**
     * 문의하기 페이지
     * @return
     */
    @GetMapping("/inquiry")
    public String about(){
        return "contact/sales_inquiry_list";
    }


    @RequestMapping(value="/inquiry/normal")
    public String normalInquiryPage(Model model){
        model.addAttribute("inquiryType", "일반문의");
        return "popup/layer_inquiry";
    }

//    @RequestMapping(value = "/inquiry/send", method=RequestMethod.POST , consumes = "application/json")
//    @ResponseBody
//    public ResponseEntity<Object> sendInquiryMail(HttpServletRequest request, HttpServletResponse response , @RequestBody Map<String,Object> params) {
//
//        // 문의 유형별 메일 설정
//        String inquiryType = (String) params.get("inquiryType");
//        String targetMail = "";
//        switch (inquiryType) {
//            case "제품 구매 문의":
//                targetMail = productMail;
//                break;
//            case "대리점 문의":
//                targetMail = distributorMail;
//                break;
//            case "사업 제휴/협업 문의":
//                targetMail = cooperationMail;
//                break;
//            case "교육 문의":
//                targetMail = educationMail;
//                break;
//        }
//
//        params.put("targetMail", targetMail);
//
//        return new ResponseEntity<>(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), inquiryService.sendMail(params)), HttpStatus.OK);
//    }

    @RequestMapping(value = "/inquiry/send", method=RequestMethod.POST , consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Object> sendInquiryMail(HttpServletRequest request, HttpServletResponse response , @RequestBody Map<String,Object> params) {

        // 문의 유형별 메일 설정
        String inquiryType = (String) params.get("inquiryType");
        String[] targetMails = getStrings(inquiryType);

        Arrays.stream(targetMails)
            .map(String::trim)
            .forEach(mail -> {
                params.put("targetMail", mail);
                inquiryService.sendMail(params);
            });

        return new ResponseEntity<>(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), "메일 발송 완료"), HttpStatus.OK);
    }

    private String[] getStrings(String inquiryType) {
        String targetMail = "";
        switch (inquiryType) {
            case "제품 구매 문의":
                targetMail = productMail;
                break;
            case "대리점 문의":
                targetMail = distributorMail;
                break;
            case "사업 제휴/협업 문의":
                targetMail = cooperationMail;
                break;
            case "교육 문의":
                targetMail = educationMail;
                break;
        }

        String[] targetMails = targetMail.split(",");
        return targetMails;
    }

}
