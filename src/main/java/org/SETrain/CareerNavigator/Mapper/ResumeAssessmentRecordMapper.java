package org.SETrain.CareerNavigator.Mapper;

import org.SETrain.CareerNavigator.Entity.ResumeAssessmentRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ResumeAssessmentRecordMapper {

    @Insert("INSERT INTO resume_assessment_record (resume_id, assessment_result, assessment_time) " +
            "VALUES (#{resumeId}, #{assessmentResult}, #{assessmentTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(ResumeAssessmentRecord record);

    @Select("SELECT * FROM resume_assessment_record WHERE resume_id = #{resumeId} ORDER BY assessment_time DESC")
    List<ResumeAssessmentRecord> getRecordsByResumeId(Integer resumeId);

    @Delete("DELETE FROM resume_assessment_record WHERE id = #{id}")
    void deleteById(Integer id);
}
