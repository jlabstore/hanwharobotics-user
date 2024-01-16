package com.hanwha.robotics.user.controller;

import com.hanwha.robotics.user.common.dto.ApiResponse;
import com.hanwha.robotics.user.common.dto.PageRequest;
import com.hanwha.robotics.user.common.dto.PageResponse;
import com.hanwha.robotics.user.common.enums.ApiStatus;
import com.hanwha.robotics.user.common.enums.NewsroomType;
import com.hanwha.robotics.user.common.utils.CommonUtil;
import com.hanwha.robotics.user.dto.*;
import com.hanwha.robotics.user.entity.Qna;
import com.hanwha.robotics.user.service.QnaReplyService;
import com.hanwha.robotics.user.service.QnaService;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/qna")
public class QnaController {

    @Autowired
    private QnaService qnaService;
    @Autowired
    private QnaReplyService qnaReplyService;
    @Autowired
    private CommonUtil commonUtil;

    /**
     * Q&A 리스트 페이지
     * @return
     */
    @GetMapping
    public String qnaPage() {
        return "contact/qna_list";
    }

    /**
     * Q&A 리스트 API
     * @param pageRequest
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> getList(PageRequest pageRequest, HttpServletRequest request) {
        String lang = commonUtil.getCookieLang(request);
        PageResponse body = qnaService.getQnaList(pageRequest, "", "", lang);
        return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), body));
    }

    /**
     * Q&A 상세 페이지
     * @param qnaNo
     * @return
     */
    @GetMapping("/{qnaNo}")
    public String qnaView(@PathVariable("qnaNo") int qnaNo){
        return "contact/qna_view";
    }

    // TODO: 글 확인은 모든 사람이 가능하나 답글 입력은 작성 회원만 할 수 있음.
    /**
     * Q&A 상세 페이지 API
     * @param qnaNo
     * @return
     */
    @PostMapping("/{qnaNo}")
    @ResponseBody
    public ResponseEntity<Object> qnaViewAPI(@PathVariable int qnaNo) {

        QnaResponse qnaDetail = qnaService.getQna(qnaNo);
        List<QnaReplyResponse> qnaReplies = qnaReplyService.getQnaReplies(qnaNo);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("qnaDetail", qnaDetail);
        responseData.put("qnaReplies", qnaReplies);

        return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), responseData));
    }

    /**
     * Q&A 등록 페이지
     * @return
     */
    @GetMapping("/register")
    public String registerPage() {
        return "contact/qna_register";
    }

    /**
     * Q&A 등록
     * @param request
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @AuthenticationPrincipal int memberNo,
            @RequestBody QnaRequest request
    ) {
        // FIXME:
        request.setMemberNo(memberNo);
        int qnaNo = qnaService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/qna/" + qnaNo).build();
    }

    // FIXME: replyType enum 으로?
    @PostMapping("/reply")
    public ResponseEntity<Void> replyRegister(
            @AuthenticationPrincipal int memberNo,
            @RequestBody QnaReplyRequest request
    ) {
        qnaReplyService.register(request);
        return ResponseEntity.ok().build();
    }



}
