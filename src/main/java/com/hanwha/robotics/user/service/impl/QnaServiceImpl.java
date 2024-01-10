package com.hanwha.robotics.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanwha.robotics.user.common.dto.PageRequest;
import com.hanwha.robotics.user.common.dto.PageResponse;
import com.hanwha.robotics.user.dto.QnaResponse;
import com.hanwha.robotics.user.mapper.QnaMapper;
import com.hanwha.robotics.user.service.QnaService;

@Service
public class QnaServiceImpl implements QnaService {

    @Autowired
    private QnaMapper qnaMapper;

    @Override
    public PageResponse getQnaList(PageRequest page, String searchType, String keyword, String lang) {
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("keyword", keyword);
        // map.put("lang", lang);

        int totalCount = qnaMapper.countQnaList();
        List<QnaResponse> responseList = qnaMapper.selectQnaList(map);
        return new PageResponse(responseList, totalCount, page);
    }

    @Override
    public QnaResponse getQna(int qnaNo) {
        QnaResponse qnaResponse = qnaMapper.selectQnaByNo(qnaNo);
        return qnaResponse;
    }


}
