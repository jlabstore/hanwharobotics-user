package com.hanwha.robotics.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanwha.robotics.user.common.entity.Newsroom;
import com.hanwha.robotics.user.common.enums.NewsroomType;
import com.hanwha.robotics.user.mapper.MainMapper;
import com.hanwha.robotics.user.service.MainService;

@Service
@Transactional
public class MainServiceImpl implements MainService{

    @Autowired
    private MainMapper mainMapper;

    @Override
    public List<Newsroom> getMainUpdates(String lang) {
        Map<String, Object> map = new HashMap<>();
        map.put("typeCd", NewsroomType.NOTICE.getCode());
        map.put("limit", 3);
        map.put("lang",lang);
        return mainMapper.selectNewsroomList(map);
    }

    @Override
    public List<Newsroom> getMainNewsroom(String lang) {
        Map<String, Object> map = new HashMap<>();
        map.put("limit", 3);
        map.put("lang",lang);
        return mainMapper.selectNewsroomList(map);
    }
    
}
