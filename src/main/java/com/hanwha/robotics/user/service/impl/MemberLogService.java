package com.hanwha.robotics.user.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanwha.robotics.user.common.enums.MemberLogType;
import com.hanwha.robotics.user.mapper.MemberLogMapper;

@Service
public class MemberLogService {

	@Autowired
	private MemberLogMapper memberLogMapper;

	// 1.
	// public void insertLoginLog(int memberNo) {
	// 	var map = new HashMap<String, Object>();
	// 	map.put("memberNo", memberNo);
	// 	map.put("logType", MemberLogType.LOGIN);
	// 	memberLogMapper.insertMemberLog(map);
	// }
	//
	// public void insertLogoutLog(int memberNo) {
	// 	var map = new HashMap<String, Object>();
	// 	map.put("memberNo", memberNo);
	// 	map.put("logType", MemberLogType.LOGOUT);
	// 	memberLogMapper.insertMemberLog(map);
	// }

	// 2.
	public void insertMemberLog(int memberNo, MemberLogType type) {
		var map = new HashMap<String, Object>();
		map.put("memberNo", memberNo);
		map.put("logType", type);
		memberLogMapper.insertMemberLog(map);
	}
}
