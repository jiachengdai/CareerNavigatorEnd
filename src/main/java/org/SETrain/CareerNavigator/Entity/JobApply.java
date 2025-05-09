package org.SETrain.CareerNavigator.Entity;

import java.util.Objects;

public class JobApply {
    private Integer id;          // 主键（对应数据库 jobapply.id）
    private String username;     // 用户名（对应数据库 jobapply.username）
    private Integer jobid;       // 岗位ID（对应数据库 jobapply.jobid）
    private String applydate;    // 申请时间（对应数据库 jobapply.applydate）
    private String status;       // 申请状态（对应数据库 jobapply.status）

    // 所有字段的 getter 方法
    public Integer getId() { return id; }
    public String getUsername() { return username; }
    public Integer getJobId() { return jobid; }
    public String getApplydate() { return applydate; }
    public String getStatus() { return status; }

    // 所有字段的 setter 方法
    public void setId(Integer id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setJobid(Integer jobid) { this.jobid = jobid; }
    public void setApplydate(String applydate) { this.applydate = applydate; }
    public void setStatus(String status) { this.status = status; }

    // 重写 toString 方法（方便调试）
    @Override
    public String toString() {
        return "JobApply{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", jobid=" + jobid +
                ", applydate='" + applydate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    // 重写 equals 和 hashCode 方法（可选）
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobApply jobApply = (JobApply) o;
        return Objects.equals(id, jobApply.id) &&
                Objects.equals(username, jobApply.username) &&
                Objects.equals(jobid, jobApply.jobid) &&
                Objects.equals(applydate, jobApply.applydate) &&
                Objects.equals(status, jobApply.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, jobid, applydate, status);
    }
}
