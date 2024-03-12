package com.hanwha.robotics.user.mapper;

import com.hanwha.robotics.user.dto.QnaRequest;
import com.hanwha.robotics.user.dto.QnaResponse;
import com.hanwha.robotics.user.dto.QnaUpdateRequest;
import com.hanwha.robotics.user.entity.Qna;
import com.hanwha.robotics.user.entity.QnaRobot;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface QnaMapper {
    int countQnaList(Map<String, Object> map);

    List<QnaResponse> selectQnaList(Map<String, Object> map);


    QnaResponse selectByQnaNo(int qnaNo);

    QnaResponse selectPrevQna(int qnaNo);

    QnaResponse selectNextQna(int qnaNo);

    List<QnaRobot> selectRobotByQnaNo(int qnaNo);



//    void insertQna(QnaRequest request);
//    int insertQna(QnaRequest request);

    int insertQna(Qna qna);


    void insertQnaRobot(QnaRobot qnaRobot);


    void updateQna(QnaUpdateRequest request);

    /**
     * Q&A 삭제
     * @param qnaNo
     */
    void deleteQna(int qnaNo);

    Qna selectQnaByQnaNo(int qnaNo);

    void deleteRobotByQnaNo(int qnaNo);

    List<QnaRobot> selectEditRobotByQnaNo(int qnaNo);
}
