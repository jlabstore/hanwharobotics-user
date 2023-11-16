package com.hanwha.robotics.user.service;

import com.hanwha.robotics.user.common.dto.PageRequest;
import com.hanwha.robotics.user.common.dto.PageResponse;
import com.hanwha.robotics.user.common.enums.NewsroomType;

public interface NewsroomService {
    public PageResponse getNewsroomList(NewsroomType newsroomType, PageRequest page);

}
