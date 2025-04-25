package org.SETrain.CareerNavigator.Service.Impl;

import org.SETrain.CareerNavigator.Entity.Projects;
import org.SETrain.CareerNavigator.Mapper.ProjectsMapper;
import org.SETrain.CareerNavigator.Service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectsServiceImpl implements ProjectsService {

  @Autowired
  private ProjectsMapper projectsMapper;

  @Override
  public List<Projects> getProjectsByResumeId(Integer resumeId) {
    return projectsMapper.getProjectsByResumeId(resumeId);
  }

  @Override
  public void insertProject(Projects project) {
    projectsMapper.insertProject(project);
  }

  @Override
  public void updateProject(Projects project) {
    projectsMapper.updateProject(project);
  }

  @Override
  public void deleteProject(Integer projectid) {
    projectsMapper.deleteProject(projectid);
  }
}