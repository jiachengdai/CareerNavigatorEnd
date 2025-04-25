package org.SETrain.CareerNavigator.Mapper;

import org.SETrain.CareerNavigator.Entity.Education;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EducationMapper {
  @Select("SELECT * FROM education WHERE resumeid = #{resumeid}")
  List<Education> getEducationByResumeId(Integer resumeid);

  @Insert("INSERT INTO education(id, school, degree, major, startDate, endDate, resumeid) " +
      "VALUES(#{id}, #{school}, #{degree}, #{major}, #{startDate}, #{endDate}, #{resumeid})")
  void insertEducation(Education education);

  @Update("UPDATE education SET school=#{school}, degree=#{degree}, major=#{major}, " +
      "startDate=#{startDate}, endDate=#{endDate}, resumeid=#{resumeid} WHERE eduid=#{eduid}")
  void updateEducation(Education education);

  @Delete("DELETE FROM education WHERE eduid = #{eduid}")
  void deleteEducation(Integer eduid);
}