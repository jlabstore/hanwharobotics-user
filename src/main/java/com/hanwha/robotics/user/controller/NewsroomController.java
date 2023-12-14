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
import com.hanwha.robotics.user.common.utils.CommonUtil;
import com.hanwha.robotics.user.service.NewsroomService;
import com.mysql.cj.util.StringUtils;

@Controller
@RequestMapping("/newsroom")
public class NewsroomController {
        

    @Autowired
    private NewsroomService newsroomService;

    @Autowired
    private CommonUtil commonUtil;


    /**
     * newsroom - 기본 notice 페이지로 이동
     * @return
     */
    @GetMapping("")
    public String main(){
        return "redirect:/newsroom/notice";
    }

    /**
     * newsroom 페이지 
     * @param type : NewsroomType 
     * @return
     */
    @GetMapping("/{type}")
    public String newsroom(@PathVariable String type){
        boolean isValid = Arrays.stream(NewsroomType.values()).anyMatch(v -> v.name().equals(type.toUpperCase()));
        String pageStr = isValid ?  type.toLowerCase() : "notice";
        return "newsroom/"+pageStr+"_list";
    }

    /**
     * newsroom 리스트 API 
     * @param type : NewsroomType 
     * @param pageRequest : 페이징 정보 
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/{type}")
    @ResponseBody
    public ResponseEntity<Object> newsroomListAPI(@PathVariable String type, PageRequest pageRequest, HttpServletRequest request, HttpServletResponse response) {
        NewsroomType newsroomType = NewsroomType.valueOf(type.toUpperCase());
        String lang = commonUtil.getCookieLang(request);
        return new ResponseEntity<>(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), newsroomService.getNewsroomList(newsroomType, pageRequest, lang)), HttpStatus.OK);
    }


    /**
     * newsroom 상세 페이지 
     * @param type : NewsroomType
     * @param no : newsroom_no
     * @return
     */
    @GetMapping("/{type}/{no}")
    public String newsroomView(@PathVariable String type, @PathVariable String no){
        // path validation check 
        // 1. type : NewsroomType 체크 및 IR 제외 
        // 2. no : 숫자 체크 
        if( Arrays.stream(NewsroomType.values()).anyMatch(v -> v.name().equals(type.toUpperCase())) 
                    && !type.toUpperCase().equals(NewsroomType.IR.toString())
                    && StringUtils.isStrictlyNumeric(no)){
                        
            String pageStr = type.toLowerCase();
            return "newsroom/"+pageStr+"_view";
        }else{
             return "redirect:/newsroom/notice";

        } 
    }

    /**
     * newsroom 상세 API 
     * @param type : NewsroomType
     * @param no : newsroom_no
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/{type}/{no}")
    @ResponseBody
    public ResponseEntity<Object> newsroomViewAPI(@PathVariable String type, @PathVariable String no, HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity<>(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), newsroomService.getNewsroom(Integer.parseInt(no))), HttpStatus.OK);
    }


}

