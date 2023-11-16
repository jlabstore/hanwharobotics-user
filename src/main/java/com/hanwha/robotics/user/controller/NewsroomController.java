package com.hanwha.robotics.user.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanwha.robotics.user.common.dto.ApiResponse;
import com.hanwha.robotics.user.common.dto.PageRequest;
import com.hanwha.robotics.user.common.enums.ApiStatus;
import com.hanwha.robotics.user.common.enums.NewsroomType;
import com.hanwha.robotics.user.service.NewsroomService;

@Controller
@RequestMapping("/newsroom")
public class NewsroomController {
        

    @Autowired
    private NewsroomService newsroomService;

    /**
     * newsroom > notice 페이지로 이동
     * @return
     */
    @GetMapping("")
    public String main(){
        return "redirect:/newsroom/notice";
    }

    /**
     * newsroom  페이지 
     * @return
     */
    @GetMapping("/{type}")
    public String notice(@PathVariable String type){
        boolean isValid = Arrays.stream(NewsroomType.values()).anyMatch(v -> v.name().equals(type.toUpperCase()));
        String pageStr = isValid ?  type.toLowerCase() : "notice";
        return "newsroom/"+pageStr+"_list";
    }


    @PostMapping("/notice")
    @ResponseBody
    public ResponseEntity<Object> noticeList(PageRequest pageRequest, HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity<>(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), newsroomService.getNewsroomList(NewsroomType.NOTICE, pageRequest)), HttpStatus.OK);
    }

    @PostMapping("/ir")
    @ResponseBody
    public ResponseEntity<Object> irList(PageRequest pageRequest, HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity<>(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), newsroomService.getNewsroomList(NewsroomType.IR, pageRequest)), HttpStatus.OK);
    }


    @PostMapping("/press")
    @ResponseBody
    public ResponseEntity<Object> pressList(PageRequest pageRequest, HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity<>(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), newsroomService.getNewsroomList(NewsroomType.PRESS, pageRequest)), HttpStatus.OK);
    }
}

