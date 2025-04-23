package org.SETrain.CareerNavigator.Service;

import org.SETrain.CareerNavigator.Entity.Honors;
import java.util.List;

public interface HonorsService {
  List<Honors> getHonorsByUserId(Integer userId);

  void insertHonor(Honors honor);

  void updateHonor(Honors honor);

  void deleteHonor(Integer honorid);
}