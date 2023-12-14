package com.hanwha.robotics.user.service;

import com.hanwha.robotics.user.common.dto.PageRequest;
import com.hanwha.robotics.user.common.dto.PageResponse;
import com.hanwha.robotics.user.common.entity.Newsroom;
import com.hanwha.robotics.user.common.enums.NewsroomType;

public interface NewsroomService {
    /**
     * newsroom 리스트
     * @param newsroomType : NewsroomType
     * @param page  : 페이징 정보
     * @return
     */
    public PageResponse getNewsroomList(NewsroomType newsroomType, PageRequest page, String lang);


    /**
     * newsroom 상세
     * @param no : newsroom_no
     * @return
     */
    public Newsroom getNewsroom(int no);


}
