package org.SETrain.CareerNavigator.Service;

import org.SETrain.CareerNavigator.Entity.AnalysisReport;

import java.util.List;

public interface AnalysisReportService {
    AnalysisReport generateReport(Integer resumeId, String userId);
    List<AnalysisReport> getReportsByUser(String userId);
    AnalysisReport getReportById(Integer id);
}
