package com.hanwha.robotics.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanwha.robotics.user.common.entity.Newsroom;
import com.hanwha.robotics.user.common.enums.NewsroomType;
import com.hanwha.robotics.user.common.utils.MailUtil;
import com.hanwha.robotics.user.mapper.MainMapper;
import com.hanwha.robotics.user.service.MainService;

@Service
@Transactional
public class MainServiceImpl implements MainService{

    @Autowired
    private MainMapper mainMapper;

    @Autowired
    private MailUtil mailUtil;

    @Override
    public List<Newsroom> getMainUpdates() {
        Map<String, Object> map = new HashMap<>();
        map.put("typeCd", NewsroomType.Notice.getCode());
        map.put("limit", 3);
        return mainMapper.selectNewsroomList(map);
    }

    @Override
    public List<Newsroom> getMainNewsroom() {
        Map<String, Object> map = new HashMap<>();
        map.put("limit", 3);
        return mainMapper.selectNewsroomList(map);
    }

    @Override
    public String sendMail(Map<String,Object> params){
        String result = "FAIL";
        // TODO Auto-generated method stub
        Boolean sendYn = mailUtil.sendMail(params);
        if(sendYn){
            result="OK";
        }
        return result;
    }
    
}
