package org.SETrain.CareerNavigator.Service;

public interface ResumeService {
    // 获取用户最新的简历ID
    Integer getLatestResumeIdByUsername(String username);
}