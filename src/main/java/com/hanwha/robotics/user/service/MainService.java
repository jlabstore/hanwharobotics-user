package com.hanwha.robotics.user.service;

import java.util.List;

import com.hanwha.robotics.user.common.entity.Newsroom;

public interface MainService {
    /**
     * 메인 > Updates 리스트
     * @return
     */
    public List<Newsroom> getMainUpdates(String lang);

    /**
     * 메인 > Newsroom 리스트
     * @return
     */
    public List<Newsroom> getMainNewsroom(String lang);

}
