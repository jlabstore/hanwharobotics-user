package com.hanwha.robotics.user.entity;

import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadFile {
    private int fileNo;
    @Setter
    private int qnaNo;
    private String originalFileNm;
    private String fileNm;
    private String typeCd;
    private Date createDt;
    private String filePath;

    @Builder
    public UploadFile(String originalFileNm, String fileNm, String typeCd, String filePath) {
        this.originalFileNm = originalFileNm;
        this.fileNm = fileNm;
        this.typeCd = typeCd;
        this.filePath = filePath;
    }

}