package com.hanwha.robotics.user.mapper;

import com.hanwha.robotics.user.dto.QnaReplyRequest;
import com.hanwha.robotics.user.dto.QnaReplyResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaReplyMapper {
    void insertQnaReply(QnaReplyRequest request);
    List<QnaReplyResponse> selectByQnaNo(int qnaNo);

    void updateQnaReply(QnaReplyRequest request);
    void deleteQnaReply(int replyNo);

}
