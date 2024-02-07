package com.hanwha.robotics.user.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hanwha.robotics.user.entity.code.Code;

@Mapper
public interface CodeMapper {

    List<Code> selectQnaCode();


    List<Code> selectQnaType1();

    List<Code> selectQnaType2();

    List<Code> selectRobotModel();

    List<Code> selectApplication();
}
