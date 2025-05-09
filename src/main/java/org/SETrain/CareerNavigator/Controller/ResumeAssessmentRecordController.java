package org.SETrain.CareerNavigator.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.SETrain.CareerNavigator.Entity.Result;
import org.SETrain.CareerNavigator.Entity.ResumeAssessmentRecord;
import org.SETrain.CareerNavigator.Service.ResumeAssessmentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resume-assessment")
public class ResumeAssessmentRecordController {
    @Autowired
    private  ResumeAssessmentRecordService recordService;

    @Operation(summary = "获取简历的评估记录列表")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "查询成功"),
        @ApiResponse(responseCode = "404", description = "无记录")
    })
    @GetMapping("/list/{resumeId}")
    public Result getRecords(@PathVariable Integer resumeId) {
        List<ResumeAssessmentRecord> records = recordService.getRecordsByResumeId(resumeId);  // Now uses imported List
        return records.isEmpty() ? Result.error("无评估记录") : Result.success(records);
    }

    @Operation(summary = "删除评估记录")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "删除成功"),
        @ApiResponse(responseCode = "500", description = "删除失败")
    })
    @DeleteMapping("/delete/{id}")
    public Result deleteRecord(@PathVariable Integer id) {
        recordService.deleteRecord(id);
        return Result.success(String.valueOf(id));  // Convert int id to String
    }
}
