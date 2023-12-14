package com.hanwha.robotics.user.common.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequest {
    private String type;//검색유형
    private String keyword;//검색어 
    private String keyword2;//검색어2
    
    private Date searchDt;//단일 날짜 검색
    private String startDt;//기간 날짜 검색
    private String endDt;//기간 날짜 검색
    private String sorting;//정렬 
    
}
