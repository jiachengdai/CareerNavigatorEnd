package org.SETrain.CareerNavigator.Mapper;

import org.SETrain.CareerNavigator.Entity.Workexperience;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WorkexperienceMapper {
  @Select("SELECT * FROM workexperience WHERE resumeid = #{resumeid}")
  List<Workexperience> getWorkexperienceByResumeId(Integer resumeid);

  @Insert("INSERT INTO workexperience(id, company, position, startdate, enddate, description, resumeid) " +
      "VALUES(#{id}, #{company}, #{position}, #{startdate}, #{enddate}, #{description}, #{resumeid})")
  void insertWorkexperience(Workexperience workexperience);

  @Update("UPDATE workexperience SET company=#{company}, position=#{position}, " +
      "startdate=#{startdate}, enddate=#{enddate}, description=#{description}, resumeid=#{resumeid} " +
      "WHERE workid=#{workid}")
  void updateWorkexperience(Workexperience workexperience);

  @Delete("DELETE FROM workexperience WHERE workid = #{workid}")
  void deleteWorkexperience(Integer workid);
}