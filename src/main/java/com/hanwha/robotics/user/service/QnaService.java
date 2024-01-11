package com.hanwha.robotics.user.service;

import com.hanwha.robotics.user.common.dto.PageRequest;
import com.hanwha.robotics.user.common.dto.PageResponse;
import com.hanwha.robotics.user.dto.QnaRequest;
import com.hanwha.robotics.user.dto.QnaResponse;

public interface QnaService {
    /**
     * Q&A 리스트
     * @param page
     * @param searchType
     * @param keyword
     * @param lang
     * @return
     */
    PageResponse getQnaList(PageRequest page, String searchType, String keyword, String lang);

    /**
     * Q&A 상세
     * @param no
     * @return
     */
    QnaResponse getQna(int no);

    int register(QnaRequest request);
}
