package org.SETrain.CareerNavigator.Service;

import org.SETrain.CareerNavigator.Entity.Resume;

public interface ResumeService {
    // 获取用户最新的简历ID
    Integer getLatestResumeIdByUsername(String username);

    Resume getResumeByUsername(String username);
}