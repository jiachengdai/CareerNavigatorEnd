package org.SETrain.CareerNavigator.Service.Impl;

import org.SETrain.CareerNavigator.Entity.Skills;
import org.SETrain.CareerNavigator.Mapper.SkillsMapper;
import org.SETrain.CareerNavigator.Service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillsServiceImpl implements SkillsService {

    @Autowired
    private SkillsMapper skillsMapper;

    @Override
    public List<Skills> getSkillsByResumeId(Integer resumeId) {
        return skillsMapper.getSkillsByResumeId(resumeId);
    }

    @Override
    public void insertSkill(Skills skill) {
        skillsMapper.insertSkill(skill);
    }

    @Override
    public void updateSkill(Skills skill) {
        skillsMapper.updateSkill(skill);
    }

    @Override
    public void deleteSkill(Integer skillid) {
        skillsMapper.deleteSkill(skillid);
    }
}