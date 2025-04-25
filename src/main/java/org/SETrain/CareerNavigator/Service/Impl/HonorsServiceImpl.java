package org.SETrain.CareerNavigator.Service.Impl;

import org.SETrain.CareerNavigator.Entity.Honors;
import org.SETrain.CareerNavigator.Mapper.HonorsMapper;
import org.SETrain.CareerNavigator.Service.HonorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HonorsServiceImpl implements HonorsService {

  @Autowired
  private HonorsMapper honorsMapper;

  @Override
  public List<Honors> getHonorsByResumeId(Integer resumeId) {
    return honorsMapper.getHonorsByResumeId(resumeId);
  }

  @Override
  public void insertHonor(Honors honor) {
    honorsMapper.insertHonor(honor);
  }

  @Override
  public void updateHonor(Honors honor) {
    honorsMapper.updateHonor(honor);
  }

  @Override
  public void deleteHonor(Integer honorid) {
    honorsMapper.deleteHonor(honorid);
  }
}