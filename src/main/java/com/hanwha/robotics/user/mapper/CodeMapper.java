package com.hanwha.robotics.user.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.hanwha.robotics.user.entity.code.Code;
import com.hanwha.robotics.user.entity.code.ParentCode;

@Mapper
public interface CodeMapper {

    List<Code> selectQnaCode();


    List<Code> selectQnaType1();

    List<Code> selectQnaType2();

    List<Code> selectRobotModel();

    List<Code> selectApplication();

    List<Map<String, Object>> selectAllCountries();

    List<Map<String, Object>> selectPhoneCodes();

    List<Code> selectAllInParentCode(List<ParentCode> parentCodes);
}
