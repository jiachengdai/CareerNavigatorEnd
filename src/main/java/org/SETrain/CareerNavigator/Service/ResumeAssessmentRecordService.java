package org.SETrain.CareerNavigator.Service;

import org.SETrain.CareerNavigator.Entity.ResumeAssessmentRecord;

import java.util.List;

public interface ResumeAssessmentRecordService {
    void createRecord(ResumeAssessmentRecord record);
    List<ResumeAssessmentRecord> getRecordsByResumeId(Integer resumeId);
    void deleteRecord(Integer id);
}
