package org.SETrain.CareerNavigator.Service.Impl;

import org.SETrain.CareerNavigator.Mapper.ResumeMapper;
import org.SETrain.CareerNavigator.Service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeMapper resumeMapper;

    @Override
    public Integer getLatestResumeIdByUsername(String username) {
        return resumeMapper.getLatestResumeIdByUsername(username);
    }
}