package com.hanwha.robotics.user.service;

import com.hanwha.robotics.user.common.dto.PageRequest;
import com.hanwha.robotics.user.common.dto.PageResponse;
import com.hanwha.robotics.user.dto.*;
import com.hanwha.robotics.user.entity.Qna;

import java.util.List;
import java.util.Optional;

public interface QnaService {
    /**
     * Q&A 리스트
     * @param page
     * @param lang
     * @return
     */
   PageResponse getQnaList(PageRequest page, String lang);

    /**
     * Q&A 상세
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

    QnaDetailResponse getQnaDetail(int memberNo, int qnaNo);

    /**
     * QNA Code
     * @return
     */
    QnaCodeResponse getQnaCode();


    void deleteQna(int memberNo, int qnaNo);

//    QnaResponse getQnaEditDetail(int qnaNo);


//    QnaDetailResponse getQnaEditDetail(int qnaNo);
    QnaDetailEditResponse getQnaDetailEdit(int qnaNo);

    /**
     * Q&A 수정
     * @param memberNo
     * @param request
     */
    void updateQna(int memberNo, QnaUpdateRequest request);
}
