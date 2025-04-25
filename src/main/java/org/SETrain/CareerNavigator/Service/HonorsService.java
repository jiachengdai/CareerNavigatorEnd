package org.SETrain.CareerNavigator.Service;

import org.SETrain.CareerNavigator.Entity.Honors;
import java.util.List;

public interface HonorsService {
  List<Honors> getHonorsByResumeId(Integer resumeId);

  void insertHonor(Honors honor);

  void updateHonor(Honors honor);

  void deleteHonor(Integer honorid);
}