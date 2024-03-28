package com.hanwha.robotics.user.controller;

import com.hanwha.robotics.user.common.dto.ApiResponse;
import com.hanwha.robotics.user.common.enums.ApiStatus;
import com.hanwha.robotics.user.common.utils.MailUtil;
import com.hanwha.robotics.user.dto.QnaReplyRequest;
import com.hanwha.robotics.user.dto.QnaReplyResponse;
import com.hanwha.robotics.user.dto.QnaResponse;
import com.hanwha.robotics.user.service.QnaReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/qna/reply")
public class QnaReplyController {

    @Autowired
    private QnaReplyService qnaReplyService;

    /**
     * Q&A 답변 등록
     * @param memberNo
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> registerReply(
            @AuthenticationPrincipal int memberNo,
            @RequestBody QnaReplyRequest request
    ) {
        request.setMemberNo(memberNo);
        qnaReplyService.register(request);
        return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name()));
    }

    /**
     * Q&A 답변 목록
     * @param qnaNo
     * @return
     */
//    @GetMapping("/{qnaNo}")
//    public ResponseEntity<Object> retrieveReplies(@PathVariable int qnaNo) {
//        List<QnaReplyResponse> response = qnaReplyService.retrieveQnaReply(qnaNo);
//        Map<String, Object> responseData = new HashMap<>();
//        responseData.put("qnaReplies", response);
//        return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), responseData));
//    }

    @GetMapping("/{qnaNo}")
    public ResponseEntity<Object> retrieveReplies(@PathVariable int qnaNo) {
        List<QnaReplyResponse> responses = qnaReplyService.retrieveQnaReply(qnaNo);
        for (QnaReplyResponse response : responses) {
            response.setMaskedMemberId();
        }
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("qnaReplies", responses);
        return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), responseData));
    }

    /**
     * Q&A 답변 수정
     * @param request
     * @return
     */
    @PutMapping("/{replyNo}")
    public ResponseEntity<Object> updateReply(
            @PathVariable int replyNo,
            @RequestBody QnaReplyRequest request
    ) {
        request.setReplyNo(replyNo);
        qnaReplyService.update(request);
        return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name()));
    }

    /**
     * Q&A 답변 삭제
     * @param replyNo
     * @return
     */
    @DeleteMapping("/{replyNo}")
    public ResponseEntity<Object> deleteReply(@PathVariable int replyNo) {
        qnaReplyService.delete(replyNo);
        return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name()));
    }



}
