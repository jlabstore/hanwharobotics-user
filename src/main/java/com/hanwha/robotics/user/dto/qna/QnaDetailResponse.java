package com.hanwha.robotics.user.dto;

import java.util.List;
import java.util.Optional;

import com.hanwha.robotics.user.entity.QnaRobot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnaDetailResponse {

	private QnaResponse qnaDetail;
	private List<QnaRobot> qnaRobots;
	private List<QnaReplyResponse> qnaReplies;
//	private Integer previousQnaNo;
//	private Integer nextQnaNo;

	private Optional<QnaResponse> previousQnaNo;
	private Optional<QnaResponse> nextQnaNo;


}
