package org.SETrain.CareerNavigator.Service;

import org.SETrain.CareerNavigator.Entity.Education;
import java.util.List;

public interface EducationService {
  List<Education> getEducationByResumeId(Integer resumeId);

  void insertEducation(Education education);

  void updateEducation(Education education);

  void deleteEducation(Integer eduid);
}