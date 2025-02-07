package com.hanwha.robotics.user.service;

import com.hanwha.robotics.user.dto.QnaReplyRequest;
import com.hanwha.robotics.user.dto.QnaReplyResponse;
import java.util.List;

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

    /**
     * 답글 수정
     * @param request
     */
    void update(QnaReplyRequest request);

    /**
     * 답글 삭제
     * @param replyNo
     */
    void delete(int replyNo);

    /**
     * 답글 리스트 조회
     * @param qnaNo
     * @return
     */
    List<QnaReplyResponse> retrieveQnaReply(int qnaNo);
}
