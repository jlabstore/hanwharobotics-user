package com.hanwha.robotics.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanwha.robotics.user.common.dto.PageRequest;
import com.hanwha.robotics.user.common.dto.PageResponse;
import com.hanwha.robotics.user.common.entity.Newsroom;
import com.hanwha.robotics.user.common.enums.NewsroomType;
import com.hanwha.robotics.user.mapper.NewsroomMapper;
import com.hanwha.robotics.user.service.NewsroomService;

@Service
@Transactional
public class NewsroomServiceImpl implements NewsroomService{


    @Autowired
    private NewsroomMapper newsroomMapper;

    @Override
    public PageResponse getNewsroomList(NewsroomType newsroomType,  PageRequest page) {
        Map<String, Object> map = new HashMap<>();
        map.put("typeCd", newsroomType.getCode());
        map.put("page", page);

        int total = newsroomMapper.selectNewsroomCnt(map);
        List<Newsroom> list = newsroomMapper.selectNewsroomList(map);
        PageResponse pageResponse = new PageResponse(list, total, page);
        return pageResponse;
    }

    @Override
    public Newsroom getNewsroom(int no) {
        return newsroomMapper.selectNewsroomByNo(no);
    }
    
}
