package com.hanwha.robotics.user.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.hanwha.robotics.user.common.utils.MailUtil;
import com.hanwha.robotics.user.dto.*;
import com.hanwha.robotics.user.entity.Qna;
import com.hanwha.robotics.user.entity.QnaRobot;
import com.hanwha.robotics.user.entity.code.ParentCode;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanwha.robotics.user.common.dto.PageRequest;
import com.hanwha.robotics.user.common.dto.PageResponse;
import com.hanwha.robotics.user.common.handler.exception.ForbiddenException;
import com.hanwha.robotics.user.common.utils.FileUtil;
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
	private UploadFileMapper uploadFileMapper;
	@Autowired
	private QnaReplyService qnaReplyService;
	@Autowired
	private FileUtil fileUtil;
	@Autowired
	private MailUtil mailUtil;


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

//		mailUtil.sendNewQnaToAdmin();
		return qnaNo;
	}


	@Override
	public QnaDetailResponse getQnaDetail(int memberNo, int qnaNo) {

		QnaResponse qnaDetail = this.getQna(qnaNo);

		// 비공개게시글 본인 여부 체크
		if ("N".equals(qnaDetail.getExposureStatus()) && memberNo != qnaDetail.getMemberNo()) {
			throw new ForbiddenException("비공개 게시글입니다.");
		}

		// 로봇 리스트
		List<QnaRobot> qnaRobots = qnaMapper.selectRobotByQnaNo(qnaNo);
		List<QnaReplyResponse> qnaReplies = qnaReplyService.getQnaReplies(qnaNo);

		// 아이디 마스킹
		qnaDetail.setMaskedMemberId();
		for (QnaReplyResponse reply : qnaReplies) {
			reply.setMaskedMemberId();
		}

		// 이전글, 다음글
		Optional<QnaResponse> previousQna = Optional.ofNullable(qnaMapper.selectPrevQna(qnaNo));
		Optional<QnaResponse> nextQna = Optional.ofNullable(qnaMapper.selectNextQna(qnaNo));

		return new QnaDetailResponse(
			qnaDetail,
			qnaRobots,
			qnaReplies,
//			previousQna.map(QnaResponse::getQnaNo).orElse(null),
//			nextQna.map(QnaResponse::getQnaNo).orElse(null)
			previousQna,
			nextQna
		);
	}


	@Override
	public QnaDetailEditResponse getQnaDetailEdit(int qnaNo) {
		QnaResponse qnaDetail = QnaResponse.of(qnaMapper.selectQnaByQnaNo(qnaNo));
		List<QnaRobot> qnaRobots = qnaMapper.selectEditRobotByQnaNo(qnaNo);
		List<UploadFile> qnaFiles = uploadFileMapper.selectByQnaNo(qnaNo);
		return new QnaDetailEditResponse(qnaDetail, qnaRobots, qnaFiles);
	}

	@Override
	@Transactional
	public void updateQna(int memberNo, QnaUpdateRequest req) {

		var qnaNo = req.qnaNo;
		if(memberNo != qnaMapper.selectByQnaNo(qnaNo).getMemberNo()) {
			throw new RuntimeException("본인글만 수정 가능합니다.");
		}

		// qna 정보 업데이트
		qnaMapper.updateQna(req);

		// 로봇 delete 후 insert
		qnaMapper.deleteRobotByQnaNo(qnaNo);
		req.getQnaRobots()
			.stream()
			.peek(robot -> robot.setQnaNo(qnaNo))
			.forEach(robot -> qnaMapper.insertQnaRobot(robot));

		// 파일 업로드
		List<UploadFile> uploadFiles = req.getFiles()
			.stream()
			.map(file -> fileUtil.uploadFile(file))
			.peek(file -> file.setQnaNo(qnaNo))
			.collect(Collectors.toList());
		if (!uploadFiles.isEmpty()) {
			uploadFileMapper.saveFile(uploadFiles);
		}

		// 파일 삭제
		if(!req.getDeleteFileIds().isEmpty()) {
			List<UploadFile> deletedFiles = uploadFileMapper.selectByIds(req.getDeleteFileIds());
			uploadFileMapper.deleteByIds(req.getDeleteFileIds());
			deletedFiles.forEach(file -> fileUtil.deleteFile(file.getFilePath()));
		}
	}


	@Override
	@Transactional
	public void deleteQna(int memberNo, int qnaNo) {
		if (memberNo != qnaMapper.selectByQnaNo(qnaNo).getMemberNo()) {
			throw new RuntimeException("본인글만 삭제 가능합니다.");
		}
		qnaMapper.deleteRobotByQnaNo(qnaNo);
		// reply 삭제
		qnaMapper.deleteQna(qnaNo);
	}




}
