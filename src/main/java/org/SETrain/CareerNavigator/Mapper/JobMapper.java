package org.SETrain.CareerNavigator.Mapper;

import org.SETrain.CareerNavigator.Entity.Job;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JobMapper {
    // Fix: Change "jobs" to "job" to match the actual table name
    @Select("SELECT * FROM job")
    List<Job> findAllJobs();

    @Select("SELECT * FROM job WHERE id = #{id}")
    Job findJobById(Integer id);



}
