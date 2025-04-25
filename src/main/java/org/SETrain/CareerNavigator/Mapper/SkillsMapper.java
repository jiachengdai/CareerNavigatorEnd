package org.SETrain.CareerNavigator.Mapper;

import org.SETrain.CareerNavigator.Entity.Skills;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SkillsMapper {

        @Select("SELECT * FROM skills WHERE resumeid = #{resumeid}")
        List<Skills> getSkillsByResumeId(Integer resumeid);

        @Insert("INSERT INTO skills(id, skillName, resumeid) " +
                        "VALUES(#{id}, #{skillName}, #{resumeid})")
        void insertSkill(Skills skill);

        @Update("UPDATE skills SET skillName=#{skillName}, resumeid=#{resumeid} " +
                        "WHERE skillid=#{skillid}")
        void updateSkill(Skills skill);

        @Delete("DELETE FROM skills WHERE skillid = #{skillid}")
        void deleteSkill(Integer skillid);
}