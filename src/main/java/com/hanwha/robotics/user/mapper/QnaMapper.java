package com.hanwha.robotics.user.mapper;

import com.hanwha.robotics.user.dto.QnaRequest;
import com.hanwha.robotics.user.dto.QnaResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface QnaMapper {
    int countQnaList();
    List<QnaResponse> selectQnaList(Map<String, Object> map);
    QnaResponse selectByQnaNo(int qnaNo);

    QnaResponse selectPrevQna(int qnaNo);

    QnaResponse selectNextQna(int qnaNo);


    int findPrevQna(int qnaNo);
    int findNextQna(int qnaNo);



    void insertQna(QnaRequest request);
}
