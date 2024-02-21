package com.hanwha.robotics.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    List<String> selectAdminEmail();

}
