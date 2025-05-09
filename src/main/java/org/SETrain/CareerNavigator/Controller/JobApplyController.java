package org.SETrain.CareerNavigator.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.SETrain.CareerNavigator.Entity.JobApply;
import org.SETrain.CareerNavigator.Entity.Result;
import org.SETrain.CareerNavigator.Service.JobApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job-applies")
public class JobApplyController {

    @Autowired
    private JobApplyService jobApplyService;

    @GetMapping("/user/{username}")
    @Operation(summary = "获取用户投递记录")
    public Result getApplyRecords(@PathVariable String username) {
        try {
            List<JobApply> records = jobApplyService.getApplyRecordsByUser(username);
            return Result.success(records);
        } catch (Exception e) {
            return Result.error("查询失败：" + e.getMessage());
        }
    }

    // 新增：创建投递记录
    @PostMapping("/create")
    @Operation(summary = "创建职位投递记录")
    public Result createJobApply(@RequestBody JobApply jobApply) {
        try {
            JobApply createdApply = jobApplyService.createJobApply(jobApply);
            return Result.success(createdApply);
        } catch (Exception e) {
            // 捕获异常并返回具体错误信息
            return Result.error("创建投递记录失败：" + e.getMessage());
        }
    }

    // 新增：更新投递状态
    @PutMapping("/update")
    @Operation(summary = "更新投递记录状态")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "更新成功"),
        @ApiResponse(responseCode = "500", description = "更新失败")
    })
    public Result updateJobApply(@RequestBody JobApply jobApply) {
        try {
            JobApply updatedApply = jobApplyService.updateJobApply(jobApply);
            return Result.success(updatedApply);
        } catch (Exception e) {
            return Result.error("更新投递记录失败：" + e.getMessage());
        }
    }

    // 新增：删除投递记录
    @DeleteMapping("/{id}")
    @Operation(summary = "删除投递记录")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "删除成功"),
        @ApiResponse(responseCode = "500", description = "删除失败")
    })
    public Result deleteJobApply(@PathVariable Integer id) {
        try {
            jobApplyService.deleteJobApply(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("删除投递记录失败：" + e.getMessage());
        }
    }

    // 新增：获取单个投递记录
    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取投递记录")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "查询成功"),
        @ApiResponse(responseCode = "404", description = "记录不存在")
    })
    public Result getJobApplyById(@PathVariable Integer id) {
        try {
            JobApply apply = jobApplyService.getJobApplyById(id);
            return apply != null ? Result.success(apply) : Result.error("投递记录不存在");
        } catch (Exception e) {
            return Result.error("查询失败：" + e.getMessage());
        }
    }
}
