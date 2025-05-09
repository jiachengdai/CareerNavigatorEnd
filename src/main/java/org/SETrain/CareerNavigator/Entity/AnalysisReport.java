package org.SETrain.CareerNavigator.Entity;

import java.util.Date;

public class AnalysisReport {
    private Integer id;
    private String userId;
    private Integer resumeId;
    private String reportContent;
    private Date generateTime;

    // Getter & Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public Integer getResumeId() { return resumeId; }
    public void setResumeId(Integer resumeId) { this.resumeId = resumeId; }
    public String getReportContent() { return reportContent; }
    public void setReportContent(String reportContent) { this.reportContent = reportContent; }
    public Date getGenerateTime() { return generateTime; }
    public void setGenerateTime(Date generateTime) { this.generateTime = generateTime; }

    @Override
    public String toString() {
        return "AnalysisReport{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", resumeId=" + resumeId +
                ", reportContent='" + reportContent + '\'' +
                ", generateTime=" + generateTime +
                '}';
    }
}