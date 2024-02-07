package com.hanwha.robotics.user.controller;

import com.hanwha.robotics.user.common.utils.FileUtil;
import com.hanwha.robotics.user.entity.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/image")
public class ImageUploadController {


    @Autowired
    private FileUtil fileUtil;


    @PostMapping
    @RequestMapping("/upload")
    public ModelAndView addImage(MultipartHttpServletRequest request) throws Exception{
        List<MultipartFile> fileList = request.getFiles("upload");
        List<UploadFile> files = fileUtil.uploadFiles(fileList);


        ModelAndView mav = new ModelAndView("jsonView");
        if(!files.isEmpty()){
            mav.addObject("uploaded", true);
            mav.addObject("url", files.get(0).getFilePath());
        }else{
            mav.addObject("uploaded", false);
            mav.addObject("url", "");
        }
        return mav;

    }
}
