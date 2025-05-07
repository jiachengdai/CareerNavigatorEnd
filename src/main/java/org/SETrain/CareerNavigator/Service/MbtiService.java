package org.SETrain.CareerNavigator.Service;

import org.SETrain.CareerNavigator.Entity.MbtiQuestion;
import org.SETrain.CareerNavigator.Entity.MbtiResult;

import java.util.List;
import java.util.Map;

public interface MbtiService {

  // 获取所有有效题目
  List<MbtiQuestion> getAllActiveQuestions();

  // 提交测试结果
  MbtiResult submitTest(String username, Map<Integer, String> answers);

  // 获取用户的所有测评记录
  List<MbtiResult> getUserTestRecords(String username);

  // 删除指定的测评记录
  boolean deleteTestRecord(Integer recordId);
}