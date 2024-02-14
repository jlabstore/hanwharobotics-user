package com.hanwha.robotics.user.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.hanwha.robotics.user.entity.Qna;
import com.hanwha.robotics.user.entity.QnaRobot;
import com.hanwha.robotics.user.entity.code.ParentCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanwha.robotics.user.common.dto.PageRequest;
import com.hanwha.robotics.user.common.dto.PageResponse;
import com.hanwha.robotics.user.common.handler.exception.ForbiddenException;
import com.hanwha.robotics.user.common.utils.FileUtil;
import com.hanwha.robotics.user.dto.QnaCodeResponse;
import com.hanwha.robotics.user.dto.QnaDetailResponse;
import com.hanwha.robotics.user.dto.QnaReplyResponse;
import com.hanwha.robotics.user.dto.QnaRequest;
import com.hanwha.robotics.user.dto.QnaResponse;
import com.hanwha.robotics.user.entity.UploadFile;
import com.hanwha.robotics.user.entity.code.Code;
import com.hanwha.robotics.user.mapper.CodeMapper;
import com.hanwha.robotics.user.mapper.UploadFileMapper;
import com.hanwha.robotics.user.mapper.QnaMapper;
import com.hanwha.robotics.user.service.QnaReplyService;
import com.hanwha.robotics.user.service.QnaService;

@Service
public class QnaServiceImpl implements QnaService {

	@Autowired
	private QnaMapper qnaMapper;
	@Autowired
	private CodeMapper codeMapper;
	@Autowired
	private QnaReplyService qnaReplyService;
	@Autowired
	private FileUtil fileUtil;
	@Autowired
	private UploadFileMapper uploadFileMapper;

	@Override
	public QnaCodeResponse getQnaCode() {
		var parentCodeList = List.of(
				ParentCode.QNA_TYPE1,
				ParentCode.QNA_TYPE2,
				ParentCode.ROBOT_MODEL,
				ParentCode.ROBOT_APPLICATION
		);
		Map<ParentCode, List<Code>> codeGroup = codeMapper.selectAllInParentCode(parentCodeList)
				.stream()
				.collect(Collectors.groupingBy(Code::getParentCode));
		return new QnaCodeResponse(codeGroup);
	}

	@Override
	public PageResponse getQnaList(PageRequest page, String lang) {
		Map<String, Object> map = new HashMap<>();
		map.put("page", page);

		int totalCount = qnaMapper.countQnaList(map);
		List<QnaResponse> responseList = qnaMapper.selectQnaList(map);
		responseList.forEach(QnaResponse::setMaskedMemberId);
		return new PageResponse(responseList, totalCount, page);
	}

	@Override
	public QnaResponse getQna(int qnaNo) {
		return qnaMapper.selectByQnaNo(qnaNo);
	}


	@Override
	@Transactional
	public int register(QnaRequest request) {
		Qna qna = request.toEntity();
		qnaMapper.insertQna(qna);
		int qnaNo = qna.getQnaNo();

		request.getQnaRobots()
			.stream()
			.peek(robot -> robot.setQnaNo(qnaNo))
			.forEach(robot -> qnaMapper.insertQnaRobot(robot));

		List<UploadFile> uploadFiles = request.getFiles()
			.stream()
			.map(file -> fileUtil.uploadFile(file))
			.peek(file -> file.setQnaNo(qnaNo))
			.collect(Collectors.toList());
		if (!uploadFiles.isEmpty()) {
			uploadFileMapper.saveFile(uploadFiles);
		}
		return qnaNo;
	}

	@Override
	public QnaDetailResponse getQnaDetail(int memberNo, int qnaNo) {
		QnaResponse qnaDetail = this.getQna(qnaNo);

		if ("N".equals(qnaDetail.getExposureStatus()) && memberNo != qnaDetail.getMemberNo()) {
			throw new ForbiddenException("비공개 게시글입니다.");
		}

		qnaDetail.setMaskedMemberId();

		List<QnaRobot> qnaRobots = qnaMapper.selectRobotByQnaNo(qnaNo);
		List<QnaReplyResponse> qnaReplies = qnaReplyService.getQnaReplies(qnaNo);

		Optional<QnaResponse> previousQna = Optional.ofNullable(qnaMapper.selectPrevQna(qnaNo));
		Optional<QnaResponse> nextQna = Optional.ofNullable(qnaMapper.selectNextQna(qnaNo));

		return new QnaDetailResponse(
			qnaDetail,
			qnaRobots,
			qnaReplies,
			previousQna.map(QnaResponse::getQnaNo).orElse(null),
			nextQna.map(QnaResponse::getQnaNo).orElse(null)
		);
	}



}
