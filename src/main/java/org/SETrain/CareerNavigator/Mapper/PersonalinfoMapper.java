package org.SETrain.CareerNavigator.Mapper;

import org.SETrain.CareerNavigator.Entity.Personalinfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PersonalinfoMapper {
  @Select("SELECT * FROM personalinfo WHERE resumeid = #{resumeid}")
  Personalinfo getPersonalInfoByResumeId(Integer resumeid);

  @Insert("INSERT INTO personalinfo(name, gender, phone, email, university, politicalStatus, website, avatar, major, age, applicationPosition, resumeid) "
      +
      "VALUES(#{name}, #{gender}, #{phone}, #{email}, #{university}, #{politicalstatus}, #{website}, #{avatar}, #{major}, #{age}, #{applicationPosition}, #{resumeid})")
  void insertPersonalInfo(Personalinfo personalinfo);

  @Update("UPDATE personalinfo SET name=#{name}, gender=#{gender}, phone=#{phone}, email=#{email}, " +
      "university=#{university}, politicalStatus=#{politicalstatus}, website=#{website}, avatar=#{avatar}, " +
      "major=#{major}, age=#{age}, applicationPosition=#{applicationPosition}, resumeid=#{resumeid} " +
      "WHERE id=#{id}")
  void updatePersonalInfo(Personalinfo personalinfo);

  @Delete("DELETE FROM personalinfo WHERE id = #{userId}")
  void deletePersonalInfo(Integer userId);
}