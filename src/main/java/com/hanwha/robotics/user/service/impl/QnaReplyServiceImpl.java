package com.hanwha.robotics.user.service.impl;

import com.hanwha.robotics.user.common.utils.MailUtil;
import com.hanwha.robotics.user.dto.QnaReplyRequest;
import com.hanwha.robotics.user.dto.QnaReplyResponse;
import com.hanwha.robotics.user.entity.QnaReply;
import com.hanwha.robotics.user.mapper.QnaMapper;
import com.hanwha.robotics.user.mapper.QnaReplyMapper;
import com.hanwha.robotics.user.service.QnaReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QnaReplyServiceImpl implements QnaReplyService {

    @Autowired
    private QnaMapper qnaMapper;
    @Autowired
    private QnaReplyMapper qnaReplyMapper;
    @Autowired
    private MailUtil mailUtil;

    @Override
    @Transactional
    public void register(QnaReplyRequest request) {
        var qna = qnaMapper.selectByQnaNo(request.getQnaNo());
        if (qna.getMemberNo() != request.getMemberNo()) {
            throw new RuntimeException("작성자만 댓글 작성이 가능합니다.");
        }
        qnaReplyMapper.insertQnaReply(request);
        mailUtil.sendNewQnaReplyToAdmin();
    }

    @Override
    public List<QnaReplyResponse> getQnaReplies(int qnaNo) {
        return qnaReplyMapper.selectByQnaNo(qnaNo);
    }

    @Override
    public void update(QnaReplyRequest request) {
        qnaReplyMapper.updateQnaReply(request);
    }

    @Override
    public void delete(int replyNo) {
        qnaReplyMapper.deleteQnaReply(replyNo);
    }


//    public List<QnaReplyResponse> retrieveQnaReply(int qnaNo) {
//        List<QnaReply> qnaReplies = qnaReplyMapper.findByQnaNo(qnaNo);
//        return qnaReplies.stream()
//                .map(QnaReplyResponse::from)
//                .collect(Collectors.toList());
//    }

    public List<QnaReplyResponse> retrieveQnaReply(int qnaNo) {
        return qnaReplyMapper.findByQnaNo(qnaNo);
    }






}
