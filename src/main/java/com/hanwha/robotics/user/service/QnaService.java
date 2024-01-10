package com.hanwha.robotics.user.service;

import com.hanwha.robotics.user.common.dto.PageRequest;
import com.hanwha.robotics.user.common.dto.PageResponse;
import com.hanwha.robotics.user.dto.QnaResponse;
import com.hanwha.robotics.user.entity.Qna;

public interface QnaService {
    PageResponse getQnaList(PageRequest page, String searchType, String keyword, String lang);
    QnaResponse getQna(int no);
}
