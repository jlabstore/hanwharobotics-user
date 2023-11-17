package com.hanwha.robotics.user.service;

import java.util.List;
import java.util.Map;

import com.hanwha.robotics.user.common.entity.Newsroom;

public interface MainService {
    /**
     * 메인 > Updates 리스트
     * @return
     */
    public List<Newsroom> getMainUpdates();

    /**
     * 메인 > Newsroom 리스트
     * @return
     */
    public List<Newsroom> getMainNewsroom();

}
