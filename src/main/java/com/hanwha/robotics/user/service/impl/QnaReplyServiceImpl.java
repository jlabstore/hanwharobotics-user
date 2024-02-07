package com.hanwha.robotics.user.service.impl;

import com.hanwha.robotics.user.dto.QnaReplyRequest;
import com.hanwha.robotics.user.dto.QnaReplyResponse;
import com.hanwha.robotics.user.mapper.QnaReplyMapper;
import com.hanwha.robotics.user.service.QnaReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaReplyServiceImpl implements QnaReplyService {

    @Autowired
    private QnaReplyMapper qnaReplyMapper;

    // TODO: 답글 입력은 작성 회원만 할 수 있게 체크
    @Override
    public void register(QnaReplyRequest request) {
        qnaReplyMapper.insertQnaReply(request);
    }

    @Override
    public List<QnaReplyResponse> getQnaReplies(int qnaNo) {
        return qnaReplyMapper.selectByQnaNo(qnaNo);
    }
}
