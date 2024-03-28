package com.hanwha.robotics.user.mapper;

import com.hanwha.robotics.user.entity.UploadFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface UploadFileMapper {

    void saveFile(List<UploadFile> files);

//    List<UploadFile> getFileListByNo(int no);

    List<UploadFile> selectByQnaNo(int qnaNo);

    List<UploadFile> selectByIds(ArrayList<Integer> deleteFileIds);

    void deleteByIds(ArrayList<Integer> deleteFileIds);
}
