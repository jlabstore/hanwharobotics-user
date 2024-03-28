package com.hanwha.robotics.user.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.hanwha.robotics.user.entity.code.Code;
import com.hanwha.robotics.user.entity.code.ParentCode;

@Mapper
public interface CodeMapper {

    List<Map<String, Object>> selectAllCountries();

    List<Map<String, Object>> selectPhoneCodes();

    List<Code> selectAllInParentCode(List<ParentCode> parentCodes);
}
