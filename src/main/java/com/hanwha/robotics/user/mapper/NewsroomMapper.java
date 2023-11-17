package com.hanwha.robotics.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.hanwha.robotics.user.common.entity.Newsroom;

@Mapper
public interface NewsroomMapper {

    public List<Newsroom> selectNewsroomList(Map<String, Object> map);
    public int selectNewsroomCnt(Map<String, Object> map);
    public Newsroom selectNewsroomByNo(int no);
    
}
