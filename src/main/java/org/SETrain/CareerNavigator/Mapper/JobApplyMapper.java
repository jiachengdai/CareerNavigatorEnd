package org.SETrain.CareerNavigator.Mapper;

import org.SETrain.CareerNavigator.Entity.JobApply;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface JobApplyMapper {

    @Select("SELECT * FROM jobapply WHERE username = #{username} ORDER BY applydate DESC")
    List<JobApply> selectByUsername(String username);

    @Insert("INSERT INTO jobapply (username, jobid, applydate, status) " +
            "VALUES (#{username}, #{jobId}, #{applydate}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(JobApply jobApply);

    @Update("UPDATE jobapply SET status = #{status} WHERE id = #{id}")
    void update(JobApply jobApply);

    @Delete("DELETE FROM jobapply WHERE id = #{id}")
    void deleteById(Integer id);

    @Select("SELECT * FROM jobapply WHERE id = #{id}")
    JobApply selectById(Integer id);

    // Add new method to check duplicate applications
    @Select("SELECT * FROM jobapply WHERE username = #{username} AND jobid = #{jobid}")
    List<JobApply> selectByUsernameAndJobId(
        @Param("username") String username,
        @Param("jobid") Integer jobid
    );
}
