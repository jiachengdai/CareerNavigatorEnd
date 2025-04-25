package org.SETrain.CareerNavigator.Service.Impl;

import org.SETrain.CareerNavigator.Entity.Personalinfo;
import org.SETrain.CareerNavigator.Mapper.PersonalinfoMapper;
import org.SETrain.CareerNavigator.Service.PersonalinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalinfoServiceImpl implements PersonalinfoService {

  @Autowired
  private PersonalinfoMapper personalinfoMapper;

  @Override
  public Personalinfo getPersonalInfoByResumeId(Integer resumeId) {
    return personalinfoMapper.getPersonalInfoByResumeId(resumeId);
  }

  @Override
  public void insertPersonalInfo(Personalinfo personalinfo) {
    personalinfoMapper.insertPersonalInfo(personalinfo);
  }

  @Override
  public void updatePersonalInfo(Personalinfo personalinfo) {
    personalinfoMapper.updatePersonalInfo(personalinfo);
  }

  @Override
  public void deletePersonalInfo(Integer userId) {
    personalinfoMapper.deletePersonalInfo(userId);
  }
}