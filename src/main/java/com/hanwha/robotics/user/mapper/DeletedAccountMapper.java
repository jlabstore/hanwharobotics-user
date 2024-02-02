package com.hanwha.robotics.user.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeletedAccountMapper {
    void insertDeletedAccount(int memberNo);
}
