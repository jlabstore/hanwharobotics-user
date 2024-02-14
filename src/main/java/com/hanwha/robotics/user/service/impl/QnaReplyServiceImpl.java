package com.hanwha.robotics.user.service.impl;

import com.hanwha.robotics.user.dto.QnaReplyRequest;
import com.hanwha.robotics.user.dto.QnaReplyResponse;
import com.hanwha.robotics.user.mapper.QnaMapper;
import com.hanwha.robotics.user.mapper.QnaReplyMapper;
import com.hanwha.robotics.user.service.QnaReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaReplyServiceImpl implements QnaReplyService {

    @Autowired
    private QnaMapper qnaMapper;
    @Autowired
    private QnaReplyMapper qnaReplyMapper;

    @Override
    public void register(QnaReplyRequest request) {
        var qna = qnaMapper.selectByQnaNo(request.getQnaNo());
        if (qna.getMemberNo() != request.getMemberNo()) {
            throw new RuntimeException("작성자만 댓글 작성이 가능합니다.");
        }
        qnaReplyMapper.insertQnaReply(request);
    }

    @Override
    public List<QnaReplyResponse> getQnaReplies(int qnaNo) {
        return qnaReplyMapper.selectByQnaNo(qnaNo);
    }
}
