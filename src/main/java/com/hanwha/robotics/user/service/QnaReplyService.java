package com.hanwha.robotics.user.service;

import com.hanwha.robotics.user.dto.QnaReplyRequest;
import com.hanwha.robotics.user.dto.QnaReplyResponse;

import java.util.List;
import java.util.Optional;

public interface QnaReplyService {

    /**
     * 답글 등록
     * @param request
     */
    void register(QnaReplyRequest request);

    /**
     * 답글 목록
     * @param qnaNo
     * @return
     */
    List<QnaReplyResponse> getQnaReplies(int qnaNo);
}
