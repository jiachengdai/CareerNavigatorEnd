package org.SETrain.CareerNavigator.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.SETrain.CareerNavigator.Entity.Resume;
import org.SETrain.CareerNavigator.Mapper.ResumeMapper;
import org.SETrain.CareerNavigator.Service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private  ResumeMapper resumeMapper;

    @Override
    public Resume getResumeByUsername(String username) {
        return resumeMapper.getResumeByUsername(username);
    }

    @Override
    public Integer getLatestResumeIdByUsername(String username) {
        return resumeMapper.getLatestResumeIdByUsername(username);
    }
    @Override
    public String assessResume(String resumeContent) {
        return "";
    }

    // 新增CRUD方法实现
    @Override
    public Resume createResume(Resume resume) {
        resumeMapper.insert(resume);
        return resume;
    }

    @Override
    public Resume getResumeById(Integer id) {
        return resumeMapper.selectById(id);
    }

    @Override
    public Resume updateResume(Resume resume) {
        resumeMapper.update(resume);
        return resume;
    }

    @Override
    public void deleteResume(Integer id) {
        resumeMapper.deleteById(id);
    }

    @Override
    public List<Resume> getResumeListByUser(String username) {
        return resumeMapper.selectByUsername(username);
    }



}
