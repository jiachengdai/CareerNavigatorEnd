package org.SETrain.CareerNavigator.Service.Impl;

import org.SETrain.CareerNavigator.Entity.Account;
import org.SETrain.CareerNavigator.Entity.Job;
import org.SETrain.CareerNavigator.Entity.JobApply;
import org.SETrain.CareerNavigator.Mapper.JobApplyMapper;
import org.SETrain.CareerNavigator.Service.AccountService;
import org.SETrain.CareerNavigator.Service.JobApplyService;
import org.SETrain.CareerNavigator.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplyServiceImpl implements JobApplyService {

    @Autowired
    private JobApplyMapper jobApplyMapper;
    @Autowired  // Added AccountService dependency
    private AccountService accountService;
    @Autowired  // Added JobService dependency
    private JobService jobService;

    @Override
    public List<JobApply> getApplyRecordsByUser(String username) {
        return jobApplyMapper.selectByUsername(username);
    }

    @Override
    public JobApply createJobApply(JobApply jobApply) {
        // 校验用户是否存在（需注入 AccountService）
        Account account = accountService.findByUsername(jobApply.getUsername());
        if (account == null) {
            throw new IllegalArgumentException("用户不存在");
        }

        // 校验岗位是否存在（需注入 JobService）
        Job job = jobService.getJobById(jobApply.getJobId());  // Fixed: getJobid() instead of getJobId()
        if (job == null) {
            throw new IllegalArgumentException("岗位不存在");
        }

        // 校验是否重复投递（可选，根据业务需求）
        List<JobApply> existing = jobApplyMapper.selectByUsernameAndJobId(
            jobApply.getUsername(),
            jobApply.getJobId()  // Fixed: getJobid() instead of getJobId()
        );
        if (!existing.isEmpty()) {
            throw new IllegalArgumentException("已投递过该岗位");
        }

        jobApplyMapper.insert(jobApply);
        return jobApply;
    }

    @Override
    public JobApply updateJobApply(JobApply jobApply) {
        jobApplyMapper.update(jobApply);
        return jobApply;
    }

    @Override
    public void deleteJobApply(Integer id) {
        jobApplyMapper.deleteById(id);
    }

    @Override
    public JobApply getJobApplyById(Integer id) {
        return jobApplyMapper.selectById(id);
    }

    // Added missing method implementation
    @Override
    public String applyJob(JobApply jobApply) {
        jobApplyMapper.insert(jobApply);  // Reuse existing insert logic
        return "Job application submitted successfully";
    }
}
