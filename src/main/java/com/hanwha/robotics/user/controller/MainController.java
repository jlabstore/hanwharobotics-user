package com.hanwha.robotics.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanwha.robotics.user.common.dto.ApiResponse;
import com.hanwha.robotics.user.common.dto.PageRequest;
import com.hanwha.robotics.user.common.enums.ApiStatus;
import com.hanwha.robotics.user.service.MainService;

@Controller
public class MainController {


    @Autowired
    private MainService mainService;

    @GetMapping("/")
    public String mainPage(){
        return "main";
    }


    /**
     * Main > Update : Notice 최신 3개
     * @param request 
     * @param response
     * @return
     */
    @RequestMapping(value = "/main/updates", method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> getMainUpdate(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity<>(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), mainService.getMainUpdates()), HttpStatus.OK);
    }

    
}
