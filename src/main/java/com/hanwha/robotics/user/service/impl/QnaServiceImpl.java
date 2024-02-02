package com.hanwha.robotics.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanwha.robotics.user.common.dto.PageRequest;
import com.hanwha.robotics.user.common.dto.PageResponse;
import com.hanwha.robotics.user.common.handler.exception.ForbiddenException;
import com.hanwha.robotics.user.dto.QnaDetailResponse;
import com.hanwha.robotics.user.dto.QnaReplyResponse;
import com.hanwha.robotics.user.dto.QnaRequest;
import com.hanwha.robotics.user.dto.QnaResponse;
import com.hanwha.robotics.user.mapper.QnaMapper;
import com.hanwha.robotics.user.service.QnaReplyService;
import com.hanwha.robotics.user.service.QnaService;

@Service
public class QnaServiceImpl implements QnaService {

	@Autowired
	private QnaMapper qnaMapper;

	@Autowired
	private QnaReplyService qnaReplyService;

	//    @Override
	//    public PageResponse getQnaList(PageRequest page, String searchType, String keyword, String lang) {
	//        Map<String, Object> map = new HashMap<>();
	//        map.put("page", page);
	//        map.put("keyword", keyword);
	//        // map.put("lang", lang);
	//
	//        int totalCount = qnaMapper.countQnaList();
	//        List<QnaResponse> responseList = qnaMapper.selectQnaList(map);
	//        return new PageResponse(responseList, totalCount, page);
	//    }

	@Override
	public PageResponse getQnaList(PageRequest page, String lang) {
		Map<String, Object> map = new HashMap<>();
		map.put("page", page);

		int totalCount = qnaMapper.countQnaList();
		List<QnaResponse> responseList = qnaMapper.selectQnaList(map);
		responseList.forEach(QnaResponse::setMaskedMemberId);
		return new PageResponse(responseList, totalCount, page);
	}

	@Override
	public QnaResponse getQna(int qnaNo) {
		return qnaMapper.selectByQnaNo(qnaNo);
	}

	@Override
	public int register(QnaRequest request) {
		qnaMapper.insertQna(request);
		return request.getQnaNo();
	}

	@Override
	public QnaDetailResponse getQnaDetail(int memberNo, int qnaNo) {
		QnaResponse qnaDetail = this.getQna(qnaNo);
		// TODO: exposure_status 가 N 인 경우 본인만 확인 가능하게 (접근못하게 알랏)
		if ("N".equals(qnaDetail.getExposureStatus()) && memberNo != qnaDetail.getMemberNo()) {
			throw new ForbiddenException("비공개 게시글입니다.");
		}

		List<QnaReplyResponse> qnaReplies = qnaReplyService.getQnaReplies(qnaNo);
		// TODO: previousQna 혹은 nextQna null 처리
		Optional<QnaResponse> previousQna = Optional.ofNullable(qnaMapper.selectPrevQna(qnaNo));
		Optional<QnaResponse> nextQna = Optional.ofNullable(qnaMapper.selectNextQna(qnaNo));

		// int previousQna = qnaMapper.findPrevQna(qnaNo);
		// int nextQna = qnaMapper.findNextQna(qnaNo);

		return new QnaDetailResponse(
			qnaDetail,
			qnaReplies,
			previousQna.map(QnaResponse::getQnaNo).orElse(null),
			nextQna.map(QnaResponse::getQnaNo).orElse(null)
		);
		// return new QnaDetailResponse(qnaDetail, qnaReplies, previousQna, nextQna);
	}


	//    @Override
	//    public int register(QnaRequest request) {
	//        // 회원 정보를 추가로 가져오는 로직
	//        Member member = memberService.findMemberById(request.getMemberId());
	//        if (member != null) {
	//            qnaMapper.insertQna(request);
	//            return request.getQnaNo();
	//        } else {
	//            throw new RuntimeException("찾을 수 없음 " + request.getMemberId());
	//        }
	//    }



	//
//	@Override
//	public QnaResponse getPrevQna(int qnaNo) {
//		return qnaMapper.selectPrevQna(qnaNo);
//	}
//
//	@Override
//	public QnaResponse getNextQna(int qnaNo) {
//		return qnaMapper.selectNextQna(qnaNo);
//	}




}
