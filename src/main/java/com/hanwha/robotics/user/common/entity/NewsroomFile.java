package com.hanwha.robotics.user.common.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class NewsroomFile {
   private int fileNo;  
   private int newsroomNo; 
   private String originalFileNm;
   private String fileNm;
   private String typeCd; 
   private String filePath; 
   private Date createDt;
}
