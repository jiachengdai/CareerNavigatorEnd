package org.SETrain.CareerNavigator.Mapper;

import org.SETrain.CareerNavigator.Entity.AnalysisReport;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnalysisReportMapper {
    @Insert("INSERT INTO resume_assessment_record(   resume_id, assessment_result, assessment_time) " +
            "VALUES (   #{resumeId}, #{reportContent}, #{generateTime})")
    void insert(AnalysisReport report);

    @Select("SELECT * FROM analysis_report WHERE user_id = #{userId}")
    List<AnalysisReport> selectByUserId(String userId);

    @Select("SELECT * FROM analysis_report WHERE id = #{id}")
    AnalysisReport selectById(Integer id);
}
