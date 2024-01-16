package com.hanwha.robotics.user.mapper;

import com.hanwha.robotics.user.dto.QnaRequest;
import com.hanwha.robotics.user.dto.QnaResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QnaMapper {
    int countQnaList();
    List<QnaResponse> selectQnaList(Map<String, Object> map);
    QnaResponse selectByQnaNo(int qnaNo);

    void insertQna(QnaRequest request);

    QnaResponse selectQnaByQnaNo(int qnaNo);
}
