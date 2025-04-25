package org.SETrain.CareerNavigator.Mapper;

import org.SETrain.CareerNavigator.Entity.Projects;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProjectsMapper {
  @Select("SELECT * FROM projects WHERE resumeid = #{resumeid}")
  List<Projects> getProjectsByResumeId(Integer resumeid);

  @Insert("INSERT INTO projects(id, projectName, role, startDate, endDate, description, briefIntroduction, resumeid) " +
      "VALUES(#{id}, #{projectName}, #{role}, #{startDate}, #{endDate}, #{description}, #{briefIntroduction}, #{resumeid})")
  void insertProject(Projects project);

  @Update("UPDATE projects SET projectName=#{projectName}, role=#{role}, startDate=#{startDate}, " +
      "endDate=#{endDate}, description=#{description}, briefIntroduction=#{briefIntroduction}, " +
      "resumeid=#{resumeid} WHERE projectid=#{projectid}")
  void updateProject(Projects project);

  @Delete("DELETE FROM projects WHERE projectid = #{projectid}")
  void deleteProject(Integer projectid);
}