package com.hanwha.robotics.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
     * newsroom > notice 페이지 
     * @return
     */
    @GetMapping("/notice")
    public ModelAndView notice(PageRequest pageRequest){
        ModelAndView mv = new ModelAndView("newsroom/notice_list");
        // mv.addObject("pageRequest", pageRequest);
        // mv.addObject("data", newsroomService.getNewsroomList(NewsroomType.Notice, pageRequest));
        return mv;
    }


    @PostMapping("/notice")
    @ResponseBody
    public ResponseEntity<Object> noticeList(PageRequest pageRequest, HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity<>(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), newsroomService.getNewsroomList(NewsroomType.Notice, pageRequest)), HttpStatus.OK);
    }
}
