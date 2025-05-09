package org.SETrain.CareerNavigator.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.SETrain.CareerNavigator.Entity.Workexperience;
import org.SETrain.CareerNavigator.Mapper.WorkexperienceMapper;
import org.SETrain.CareerNavigator.Service.WorkexperienceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkexperienceServiceImpl implements WorkexperienceService {

    private final WorkexperienceMapper workexperienceMapper;

    @Override
    public List<Workexperience> getWorkexperienceByResumeId(Integer resumeId) {
        return workexperienceMapper.getWorkexperienceByResumeId(resumeId);
    }

    @Override
    public void insertWorkexperience(Workexperience workexperience) {
        workexperienceMapper.insertWorkexperience(workexperience);
    }

    @Override
    public void updateWorkexperience(Workexperience workexperience) {
        workexperienceMapper.updateWorkexperience(workexperience);
    }

    @Override
    public void deleteWorkexperience(Integer workid) {
        workexperienceMapper.deleteWorkexperience(workid);
    }
}
