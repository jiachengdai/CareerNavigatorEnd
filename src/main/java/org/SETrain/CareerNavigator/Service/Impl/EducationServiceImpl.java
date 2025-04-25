package org.SETrain.CareerNavigator.Service.Impl;

import org.SETrain.CareerNavigator.Entity.Education;
import org.SETrain.CareerNavigator.Mapper.EducationMapper;
import org.SETrain.CareerNavigator.Service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {

  @Autowired
  private EducationMapper educationMapper;

  @Override
  public List<Education> getEducationByResumeId(Integer resumeId) {
    return educationMapper.getEducationByResumeId(resumeId);
  }

  @Override
  public void insertEducation(Education education) {
    educationMapper.insertEducation(education);
  }

  @Override
  public void updateEducation(Education education) {
    educationMapper.updateEducation(education);
  }

  @Override
  public void deleteEducation(Integer eduid) {
    educationMapper.deleteEducation(eduid);
  }
}