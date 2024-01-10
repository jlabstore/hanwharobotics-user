package com.hanwha.robotics.user.controller;

import com.hanwha.robotics.user.common.dto.ApiResponse;
import com.hanwha.robotics.user.common.dto.PageRequest;
import com.hanwha.robotics.user.common.dto.PageResponse;
import com.hanwha.robotics.user.common.enums.ApiStatus;
import com.hanwha.robotics.user.common.enums.NewsroomType;
import com.hanwha.robotics.user.common.utils.CommonUtil;
import com.hanwha.robotics.user.dto.QnaRequest;
import com.hanwha.robotics.user.entity.Qna;
import com.hanwha.robotics.user.service.QnaService;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Controller
@RequestMapping("/qna")
public class QnaController {

    @Autowired
    private QnaService qnaService;
    @Autowired
    private CommonUtil commonUtil;

    @GetMapping
    public String qnaPage() {
        return "contact/qna_list";
    }

    @PostMapping
    public ResponseEntity<Object> getList(PageRequest pageRequest, HttpServletRequest request) {
        String lang = commonUtil.getCookieLang(request);
        PageResponse body = qnaService.getQnaList(pageRequest, "", "", lang);
        return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), body));
    }

    @GetMapping("/{qnaNo}")
    public String qnaView(@PathVariable("qnaNo") int qnaNo){
            return "contact/qna_view";
    }

    // TODO: 글 확인은 모든 사람이 가능하나 답글 입력은 작성 회원만 할 수 있음.

    @PostMapping("/{qnaNo}")
    @ResponseBody
    public ResponseEntity<Object> qnaViewAPI(@PathVariable int qnaNo) {
        return new ResponseEntity<>(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), qnaService.getQna(qnaNo)), HttpStatus.OK);
    }







}
