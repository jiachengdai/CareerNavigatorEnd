package org.SETrain.CareerNavigator.Service;

import org.SETrain.CareerNavigator.Entity.Projects;
import java.util.List;

public interface ProjectsService {
  List<Projects> getProjectsByUserId(Integer userId);

  void insertProject(Projects project);

  void updateProject(Projects project);

  void deleteProject(Integer projectid);
}