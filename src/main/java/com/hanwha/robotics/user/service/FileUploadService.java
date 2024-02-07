package com.hanwha.robotics.user.service;

import com.hanwha.robotics.user.entity.UploadFile;
import com.hanwha.robotics.user.mapper.UploadFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    @Autowired
    UploadFileMapper mapper;

    @Transactional
    public void saveFile(int qnaNo, List<UploadFile> files){
        if(CollectionUtils.isEmpty(files)){
            return;
        }
        for (UploadFile file : files) {
            file.setQnaNo(qnaNo);
        }
        mapper.saveFile(files);
    }



    public List<UploadFile> getFileListByNo(int no) {
        return mapper.getFileListByNo(no);
    }




}