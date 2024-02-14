package com.hanwha.robotics.user.controller;

import com.hanwha.robotics.user.common.dto.ApiResponse;
import com.hanwha.robotics.user.common.dto.PageRequest;
import com.hanwha.robotics.user.common.dto.PageResponse;
import com.hanwha.robotics.user.common.enums.ApiStatus;
import com.hanwha.robotics.user.common.utils.CommonUtil;
import com.hanwha.robotics.user.dto.*;
import com.hanwha.robotics.user.service.MemberService;
import com.hanwha.robotics.user.service.QnaReplyService;
import com.hanwha.robotics.user.service.QnaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/qna")
public class QnaController {

    @Autowired
    private QnaService qnaService;
    @Autowired
    private MemberService memberService;
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

    // TODO: lang
    /**
     * Q&A 리스트 API
     * @param pageRequest
     * @param request
     * @return
     */
   @PostMapping
   public ResponseEntity<Object> getList(
           PageRequest pageRequest,
           HttpServletRequest request
   ) {
       String lang = commonUtil.getCookieLang(request);
       PageResponse body = qnaService.getQnaList(pageRequest, lang);
       return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), body));
   }

    /**
     * Q&A 상세 페이지
     * @param qnaNo
     * @return
     */
    @GetMapping("/{qnaNo}")
    public String qnaView(@PathVariable("qnaNo") int qnaNo) {
        return "contact/qna_view";
    }


    /**
     * Q&A 상세 페이지
     * @param qnaNo
     * @return
     */
    @PostMapping("/{qnaNo}")
    @ResponseBody
    public ResponseEntity<Object> qnaViewAPI(
            @PathVariable int qnaNo,
            @AuthenticationPrincipal int memberNo
    ) {
        QnaDetailResponse qnaDetailResponse = qnaService.getQnaDetail(memberNo, qnaNo);
        return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), qnaDetailResponse));
    }


    @GetMapping("/edit/{qnaNo}")
    public String qnaEditPage(@PathVariable("qnaNo") int qnaNo) {
        return "contact/qna_edit";
    }


    /**
     * Q&A 등록 페이지
     * @return
     */
    @GetMapping("/register")
    public String registerPage(
            @AuthenticationPrincipal int memberNo,
            Model model
    ) {
        MemberResponse memberResponse = memberService.retrieve(memberNo);
        QnaCodeResponse qnaCodeResponse = qnaService.getQnaCode();
        model.addAttribute("memberResponse", memberResponse);
        model.addAttribute("qnaCodeResponse", qnaCodeResponse);
        return "contact/qna_register";
    }

    @GetMapping ("/code")
    public ResponseEntity<Object> getQnaCode() {
        // TODO:
        QnaCodeResponse qnaCodeResponse = qnaService.getQnaCode();
        return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), qnaCodeResponse));
    }



    /**
     * Q&A 등록
     * @param request
     * @return
     */
    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Integer> register(
            @AuthenticationPrincipal int memberNo,
            @ModelAttribute QnaRequest request
    ) {
        // FIXME:
        request.setMemberNo(memberNo);
        int qnaNo = qnaService.register(request);
        return ResponseEntity.ok().body(qnaNo);
//        return ResponseEntity.ok().header("Location", "/qna/" + qnaNo).build();
    }


//    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public String registerQna(
//            @AuthenticationPrincipal int memberNo,
//            @ModelAttribute QnaRequest request
//    ) {
//        request.setMemberNo(memberNo);
//        int qnaNo = qnaService.register(request);
//        return "redirect:/qna/" + qnaNo;
//    }



    // FIXME: replyType enum
    @PostMapping("/reply")
    public ResponseEntity<Object> replyRegister(
            @AuthenticationPrincipal int memberNo,
            @RequestBody QnaReplyRequest request
    ) {
        request.setMemberNo(memberNo);
        qnaReplyService.register(request);
        return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name()));
    }



}
