package org.SETrain.CareerNavigator.Service.Impl;

import org.SETrain.CareerNavigator.Entity.AnalysisReport;
import org.SETrain.CareerNavigator.Entity.Resume;
import org.SETrain.CareerNavigator.Mapper.AnalysisReportMapper;
import org.SETrain.CareerNavigator.Service.AnalysisReportService;
import org.SETrain.CareerNavigator.Service.ResumeService;
import org.springframework.ai.chat.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AnalysisReportServiceImpl implements AnalysisReportService {

    @Autowired
    private ResumeService resumeService;
    @Autowired
    private ChatClient chatClient;
    @Autowired
    private AnalysisReportMapper analysisReportMapper;  // Requires AnalysisReportMapper to exist

    @Override
    public AnalysisReport generateReport(Integer resumeId, String userId) {
        Resume resume = resumeService.getResumeById(resumeId);
        if (resume == null) throw new RuntimeException("简历不存在");
        String prePrompt="你是一个专业HR，请从专业技能匹配度、项目经验、教育背景、格式规范等维度进行简历评估，返回JSON格式：{score: 0-100, analysis: string, suggestions: string[]}.下面是简历的内容：";
        String reportContent = chatClient.call(prePrompt+resume.getContent());  // Requires Resume.getContent() to exist
        AnalysisReport report = new AnalysisReport();
        report.setUserId(userId);
        report.setResumeId(resumeId);
        report.setReportContent(reportContent);
        report.setGenerateTime(new Date());

        analysisReportMapper.insert(report);
        return report;
    }

    @Override
    public List<AnalysisReport> getReportsByUser(String userId) {  // Now uses List due to added import
        return analysisReportMapper.selectByUserId(userId);
    }

    @Override
    public AnalysisReport getReportById(Integer id) {
        return analysisReportMapper.selectById(id);
    }
}
