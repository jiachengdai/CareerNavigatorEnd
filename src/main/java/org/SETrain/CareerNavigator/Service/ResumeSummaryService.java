package org.SETrain.CareerNavigator.Service;

/**
 * 简历信息汇总服务
 */
public interface ResumeSummaryService {
  /**
   * 获取用户完整的简历信息
   * 
   * @param username 用户名
   * @return 格式化的简历信息字符串
   */
  String getResumeSummary(String username);
}