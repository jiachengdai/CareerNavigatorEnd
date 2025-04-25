package org.SETrain.CareerNavigator.Mapper;

import org.SETrain.CareerNavigator.Entity.Honors;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HonorsMapper {
    @Select("SELECT * FROM honors WHERE resumeid = #{resumeid}")
    List<Honors> getHonorsByResumeId(Integer resumeid);

    @Insert("INSERT INTO honors(id, honorName, date, description, resumeid) " +
            "VALUES(#{id}, #{honorName}, #{date}, #{description}, #{resumeid})")
    void insertHonor(Honors honor);

    @Update("UPDATE honors SET honorName=#{honorName}, date=#{date}, " +
            "description=#{description}, resumeid=#{resumeid} WHERE honorid=#{honorid}")
    void updateHonor(Honors honor);

    @Delete("DELETE FROM honors WHERE honorid = #{honorid}")
    void deleteHonor(Integer honorid);
}