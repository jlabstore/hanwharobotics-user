package com.hanwha.robotics.user.controller;

import com.hanwha.robotics.user.common.dto.ApiResponse;
import com.hanwha.robotics.user.common.dto.PageRequest;
import com.hanwha.robotics.user.common.dto.PageResponse;
import com.hanwha.robotics.user.common.enums.ApiStatus;
import com.hanwha.robotics.user.common.enums.NewsroomType;
import com.hanwha.robotics.user.common.utils.CommonUtil;
import com.hanwha.robotics.user.dto.*;
import com.hanwha.robotics.user.entity.Qna;
import com.hanwha.robotics.user.service.MemberService;
import com.hanwha.robotics.user.service.QnaReplyService;
import com.hanwha.robotics.user.service.QnaService;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
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

    /**
     * Q&A 리스트 API
     * @param pageRequest
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> getList(PageRequest pageRequest, HttpServletRequest request) {
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
    public String qnaView(@PathVariable("qnaNo") int qnaNo){
        return "contact/qna_view";
    }

    // TODO: 답글 입력은 작성 회원만 할 수 있음
    // TODO: exposure_status 가 N 인 경우 본인만 확인 가능하게 (접근못하게 알랏)
    // TODO: (이전글, 다음글) 첫글 마지막글 qnaNo null 처리
    /**
     * Q&A 상세 페이지 API
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



    // 리팩 전 - qnaNo null 이어도 읽어는 와짐
//    @PostMapping("/{qnaNo}")
//    @ResponseBody
//    public ResponseEntity<Object> qnaViewAPI(
//            @PathVariable int qnaNo,
//            @AuthenticationPrincipal int memberNo
//    ) {
//
//        QnaResponse qnaDetail = qnaService.getQna(qnaNo);
//        List<QnaReplyResponse> qnaReplies = qnaReplyService.getQnaReplies(qnaNo);
//
//        // TODO: exposure_status 가 N 인 경우 본인만 확인 가능하게 (접근못하게 알랏)
//        if ("N".equals(qnaDetail.getExposureStatus()) && memberNo != qnaDetail.getMemberNo()) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN)
//                    .body(ApiResponse.res(ApiStatus.FORBIDDEN.getValue(), "비공개 게시글입니다."));
//        }
//
//        QnaResponse previousQna = qnaService.getPrevQna(qnaNo);
//        QnaResponse nextQna = qnaService.getNextQna(qnaNo);
//
//        Map<String, Object> responseData = new HashMap<>();
//        responseData.put("qnaDetail", qnaDetail);
//        responseData.put("qnaReplies", qnaReplies);
//        responseData.put("previousQna", previousQna);
//        responseData.put("nextQna", nextQna);
//
//        return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), responseData));
//    }



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
        model.addAttribute("memberResponse", memberResponse);
        return "contact/qna_register";
    }

    /**
     * Q&A 등록
     * @param request
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<Integer> register(
            @AuthenticationPrincipal int memberNo,
            @RequestBody QnaRequest request
    ) {
        // FIXME:
        request.setMemberNo(memberNo);
        int qnaNo = qnaService.register(request);
        return ResponseEntity.ok().body(qnaNo);
//        return ResponseEntity.ok().header("Location", "/qna/" + qnaNo).build();
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
