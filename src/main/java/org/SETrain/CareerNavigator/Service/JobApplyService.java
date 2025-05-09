package org.SETrain.CareerNavigator.Service;

import org.SETrain.CareerNavigator.Entity.JobApply;

import java.util.List;

public interface JobApplyService {
    List<JobApply> getApplyRecordsByUser(String username);
    JobApply createJobApply(JobApply jobApply);
    JobApply updateJobApply(JobApply jobApply);
    void deleteJobApply(Integer id);
    JobApply getJobApplyById(Integer id);
    String applyJob(JobApply jobApply);
}
