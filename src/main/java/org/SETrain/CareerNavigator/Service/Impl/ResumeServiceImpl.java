package org.SETrain.CareerNavigator.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.SETrain.CareerNavigator.Entity.Resume;
import org.SETrain.CareerNavigator.Mapper.ResumeMapper;
import org.SETrain.CareerNavigator.Service.ResumeService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeMapper resumeMapper;

    @Override
    public Resume getResumeByUsername(String username) {
        return resumeMapper.getResumeByUsername(username);
    }

    @Override
    public Integer getLatestResumeIdByUsername(String username) {
        return resumeMapper.getLatestResumeIdByUsername(username);
    }
}