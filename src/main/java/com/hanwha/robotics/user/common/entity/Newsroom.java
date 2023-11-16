package com.hanwha.robotics.user.common.entity;

import java.sql.Date;

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

}
