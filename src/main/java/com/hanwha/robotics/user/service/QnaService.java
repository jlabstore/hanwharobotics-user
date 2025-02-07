package com.hanwha.robotics.user.service;

import com.hanwha.robotics.user.common.dto.PageRequest;
import com.hanwha.robotics.user.common.dto.PageResponse;
import com.hanwha.robotics.user.dto.*;

public interface QnaService {
    /**
     * Q&A 리스트
     * @param page
     * @param lang
     * @return
     */
   PageResponse getQnaList(PageRequest page, String lang);

    /**
     * Q&A 조회
     * @param qnaNo
     * @return
     */
    QnaResponse getQna(int qnaNo);

    /**
     * Q&A 등록
     * @param request
     * @return
     */
    int register(QnaRequest request);

    /**
     * Q&A 상세 조회
     * @param memberNo
     * @param qnaNo
     * @return
     */
    QnaDetailResponse getQnaDetail(int memberNo, int qnaNo);

    /**
     * Q&A Code
     * @return
     */
    QnaCodeResponse getQnaCode();

    QnaEnCodeResponse getEnQnaCode();

    /**
     * Q&A 삭제
     * @param memberNo
     * @param qnaNo
     */
    void deleteQna(int memberNo, int qnaNo);

    /**
     * Q&A 수정 정보 조회
     * @param qnaNo
     * @return
     */
    QnaDetailEditResponse getQnaDetailEdit(int qnaNo);

    /**
     * Q&A 수정
     * @param memberNo
     * @param request
     */
    void updateQna(int memberNo, QnaUpdateRequest request);

    /**
     * Q&A 존재여부 조회
     * @param qnaNo
     * @return
     */
    boolean existsById(int qnaNo);
}
