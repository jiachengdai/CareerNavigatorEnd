package org.SETrain.CareerNavigator.Service;

import org.SETrain.CareerNavigator.Entity.Personalinfo;

public interface PersonalinfoService {
  Personalinfo getPersonalInfoByUserId(Integer userId);

  void insertPersonalInfo(Personalinfo personalinfo);

  void updatePersonalInfo(Personalinfo personalinfo);

  void deletePersonalInfo(Integer userId);
}