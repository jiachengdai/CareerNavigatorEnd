package org.SETrain.CareerNavigator.Entity;

import java.util.Date;
import java.util.Objects;

public class ResumeAssessmentRecord {
    private Integer id;
    private Integer resumeId;  // 关联的简历ID
    private String assessmentResult;  // 评估结果内容
    private Date assessmentTime;  // 评估时间
    public ResumeAssessmentRecord() {}
    public ResumeAssessmentRecord( Integer resumeId, String assessmentResult, Date assessmentTime) {

        this.resumeId = resumeId;
        this.assessmentResult = assessmentResult;
        this.assessmentTime = assessmentTime;
    }

    @Override
    public String toString() {
        return "ResumeAssessmentRecord{" +
                "id=" + id +
                ", resumeId=" + resumeId +
                ", assessmentResult='" + assessmentResult + '\'' +
                ", assessmentTime='" + assessmentTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResumeAssessmentRecord that = (ResumeAssessmentRecord) o;
        return Objects.equals(id, that.id) && Objects.equals(resumeId, that.resumeId) && Objects.equals(assessmentResult, that.assessmentResult) && Objects.equals(assessmentTime, that.assessmentTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, resumeId, assessmentResult, assessmentTime);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    public String getAssessmentResult() {
        return assessmentResult;
    }

    public void setAssessmentResult(String assessmentResult) {
        this.assessmentResult = assessmentResult;
    }

    public Date getAssessmentTime() {
        return assessmentTime;
    }

    public void setAssessmentTime(Date assessmentTime) {
        this.assessmentTime = assessmentTime;
    }
}
