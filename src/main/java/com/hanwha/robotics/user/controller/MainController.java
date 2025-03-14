package com.hanwha.robotics.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanwha.robotics.user.common.dto.ApiResponse;
import com.hanwha.robotics.user.common.enums.ApiStatus;
import com.hanwha.robotics.user.common.utils.CommonUtil;
import com.hanwha.robotics.user.service.MainService;

@Controller
public class MainController {


    @Autowired
    private MainService mainService;


    @Autowired
    private CommonUtil commonUtil;
    



    /**
     * Main 페이지 
     * @return
     */
    @GetMapping("/")
    public String mainPage(){
        return "main";
    }


    
    /**
     * Main > Update API : Notice 최신 3개
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/main/updates")
    @ResponseBody
    public ResponseEntity<Object> getMainUpdates(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity<>(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), mainService.getMainUpdates(commonUtil.getCookieLang(request))), HttpStatus.OK);
    }

    

    /**
     * Main > Newsroom API : Newsroom 최신 3개
     * @param request 
     * @param response
     * @return
     */
    @PostMapping("/main/newsroom")
    @ResponseBody
    public ResponseEntity<Object> getMainNewsroom(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity<>(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), mainService.getMainNewsroom(commonUtil.getCookieLang(request))), HttpStatus.OK);
    }

}
