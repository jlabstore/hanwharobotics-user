package com.hanwha.robotics.user.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnaDetailResponse {

	private QnaResponse qnaDetail;
	private List<QnaReplyResponse> qnaReplies;
	private Integer previousQnaNo;
	private Integer nextQnaNo;
}
