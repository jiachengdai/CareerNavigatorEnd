package org.SETrain.CareerNavigator.Service;

import org.SETrain.CareerNavigator.Entity.Job;

import java.util.List;

public interface JobService {
    List<Job> getAllJobs();
    Job getJobById(Integer id);
    // 职位解读（输入岗位ID，输出结构化解读文本）
    String analyzeJob(Integer jobId);
 }
