package org.SETrain.CareerNavigator.Controller;

import org.SETrain.CareerNavigator.Entity.Job;
import org.SETrain.CareerNavigator.Entity.Result;
import org.SETrain.CareerNavigator.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping
    public Result<List<Job>> getAllJobs() {  // Parameterized Result with List<Job>
        return Result.success(jobService.getAllJobs());
    }

    /**
     * 根据 ID 查询单个岗位信息
     * @param id 岗位 ID
     * @return 岗位信息或错误提示
     */
    @GetMapping("/{id}")
    public Result<Job> getJobById(@PathVariable Integer id) {  // Parameterized Result with Job
        Job job = jobService.getJobById(id);
        return job != null ? Result.success(job) : Result.error("未找到对应岗位信息");
    }

    /**
     * 职位解读接口（调用AI生成结构化解读）
     * @param jobId 岗位ID
     * @return 解读文本（如格式错误返回错误提示）
     */
    @GetMapping("/analyze/{jobId}")
    public Result<String> analyzeJob(@PathVariable Integer jobId) {
        String analysis = jobService.analyzeJob(jobId);
        return analysis.contains("失败") ? Result.error(analysis) : Result.success(analysis);
    }


}
