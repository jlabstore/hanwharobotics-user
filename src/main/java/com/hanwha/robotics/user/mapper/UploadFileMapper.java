package com.hanwha.robotics.user.mapper;

import com.hanwha.robotics.user.entity.UploadFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UploadFileMapper {

    public void saveFile(List<UploadFile> files);

    List<UploadFile> getFileListByNo(int no);


}
