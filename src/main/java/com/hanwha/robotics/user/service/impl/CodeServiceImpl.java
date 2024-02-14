package com.hanwha.robotics.user.service.impl;

import com.hanwha.robotics.user.mapper.CodeMapper;
import com.hanwha.robotics.user.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeMapper codeMapper;

    @Override
    public List<Map<String, Object>> getAllCountries() {
        return codeMapper.selectAllCountries();
    }

    @Override
    public List<Map<String, Object>> getPhoneCodes() {
        return codeMapper.selectPhoneCodes();
    }
}
