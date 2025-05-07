package org.SETrain.CareerNavigator.Controller;

import org.SETrain.CareerNavigator.Entity.MbtiQuestion;
import org.SETrain.CareerNavigator.Entity.MbtiResult;
import org.SETrain.CareerNavigator.Entity.Result;
import org.SETrain.CareerNavigator.Service.MbtiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mbti")
public class MbtiController {

  @Autowired
  private MbtiService mbtiService;

  // 获取所有题目
  @GetMapping("/questions")
  public Result getAllQuestions() {
    List<MbtiQuestion> questions = mbtiService.getAllActiveQuestions();
    return Result.success(questions);
  }

  // 提交测试结果
  @PostMapping("/submit")
  public Result submitTest(
      @RequestParam String username,
      @RequestBody Map<Integer, String> answers) { // key是题目ID，value是用户选择的选项文本
    MbtiResult result = mbtiService.submitTest(username, answers);
    return Result.success(result);
  }

  // 获取用户的所有测评记录
  @GetMapping("/records/{username}")
  public Result getUserRecords(@PathVariable String username) {
    List<MbtiResult> records = mbtiService.getUserTestRecords(username);
    return Result.success(records);
  }

  // 删除指定的测评记录
  @DeleteMapping("/records/{recordId}")
  public Result deleteRecord(@PathVariable Integer recordId) {
    boolean success = mbtiService.deleteTestRecord(recordId);
    return Result.success(success);
  }
}