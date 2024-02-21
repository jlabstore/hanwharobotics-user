package com.hanwha.robotics.user.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hanwha.robotics.user.entity.QnaRobot;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaUpdateRequest {

	public int qnaNo;
	public String email;
	public String title;
	public String content;
	public String qnaType1Cd;
	public String qnaType2Cd;
	public String emailReceiveYn;

	private ArrayList<QnaRobot> qnaRobots;

	private ArrayList<Integer> deleteFileIds = new ArrayList<>();
	private ArrayList<MultipartFile> files = new ArrayList<>();

}
