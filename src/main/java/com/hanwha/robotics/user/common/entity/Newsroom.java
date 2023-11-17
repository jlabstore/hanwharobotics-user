package com.hanwha.robotics.user.common.entity;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class Newsroom {
   private int newsroomNo;
   private String typeCd;
   private String title;
   private Date createDt;
   private String content;
   private String showYn;
   private String categoryCd;

   //IR
   private String originalFileNm;
   private String filePath;

   private List<NewsroomFile> files;

}
