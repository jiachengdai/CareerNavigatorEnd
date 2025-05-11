package org.SETrain.CareerNavigator.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.SETrain.CareerNavigator.Entity.AnalysisReport;
import org.SETrain.CareerNavigator.Entity.Result;
import org.SETrain.CareerNavigator.Service.AnalysisReportService;
import org.SETrain.CareerNavigator.Util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/analysis-report")
public class AnalysisReportController {
    @Autowired
    private  AnalysisReportService analysisReportService;


    @PostMapping("/generate")
    @Operation(summary = "生成分析报告")
    public Result generateReport(@RequestParam Integer resumeId ) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        String userId = (String) claims.get("username");

        AnalysisReport report = analysisReportService.generateReport(resumeId, userId);
        return Result.success(report);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "获取用户的分析报告列表")
    public Result getReportsByUser(@PathVariable String userId) {
        List<AnalysisReport> reports = analysisReportService.getReportsByUser(userId);
        return Result.success(reports);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取分析报告")
    public Result getReportById(@PathVariable Integer id) {
        AnalysisReport report = analysisReportService.getReportById(id);
        return report != null ? Result.success(report) : Result.error("报告不存在");
    }
}
