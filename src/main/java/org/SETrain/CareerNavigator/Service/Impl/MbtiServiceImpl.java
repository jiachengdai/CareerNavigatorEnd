package org.SETrain.CareerNavigator.Service.Impl;

import org.SETrain.CareerNavigator.Entity.MbtiQuestion;
import org.SETrain.CareerNavigator.Entity.MbtiResult;
import org.SETrain.CareerNavigator.Mapper.MbtiOptionMapper;
import org.SETrain.CareerNavigator.Mapper.MbtiQuestionMapper;
import org.SETrain.CareerNavigator.Mapper.MbtiResultMapper;
import org.SETrain.CareerNavigator.Service.MbtiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MbtiServiceImpl implements MbtiService {

  @Autowired
  private MbtiQuestionMapper questionMapper;

  @Autowired
  private MbtiOptionMapper optionMapper;

  @Autowired
  private MbtiResultMapper resultMapper;

  @Override
  public List<MbtiQuestion> getAllActiveQuestions() {
    return questionMapper.findAllActiveQuestions();
  }

  @Override
  public MbtiResult submitTest(String username, Map<Integer, String> answers) {
    // 初始化分数
    int eScore = 0, iScore = 0;
    int sScore = 0, nScore = 0;
    int tScore = 0, fScore = 0;
    int jScore = 0, pScore = 0;

    // 计算各维度得分
    for (Map.Entry<Integer, String> entry : answers.entrySet()) {
      Integer questionId = entry.getKey();
      String selectedOptionText = entry.getValue();

      System.out.println("处理问题ID: " + questionId + ", 选择的答案: " + selectedOptionText);

      // 获取选项的维度和分数
      String dimension = optionMapper.getDimensionValueByQuestionAndText(questionId, selectedOptionText);
      Integer score = optionMapper.getScoreByQuestionAndText(questionId, selectedOptionText);

      System.out.println("获取到的维度: " + dimension + ", 分数: " + score);

      // 添加空值检查
      if (dimension != null && score != null) {
        // 累加分数
        switch (dimension) {
          case "E":
            eScore += score;
            break;
          case "I":
            iScore += score;
            break;
          case "S":
            sScore += score;
            break;
          case "N":
            nScore += score;
            break;
          case "T":
            tScore += score;
            break;
          case "F":
            fScore += score;
            break;
          case "J":
            jScore += score;
            break;
          case "P":
            pScore += score;
            break;
        }
      }
    }

    System.out.println("最终分数: E=" + eScore + ", I=" + iScore +
        ", S=" + sScore + ", N=" + nScore +
        ", T=" + tScore + ", F=" + fScore +
        ", J=" + jScore + ", P=" + pScore);

    // 确定MBTI类型
    String mbtiType = "";
    mbtiType += (eScore > iScore) ? "E" : "I";
    mbtiType += (sScore > nScore) ? "S" : "N";
    mbtiType += (tScore > fScore) ? "T" : "F";
    mbtiType += (jScore > pScore) ? "J" : "P";

    // 使用Mapper保存结果
    resultMapper.insertResult(
        username,
        mbtiType,
        eScore,
        iScore,
        sScore,
        nScore,
        tScore,
        fScore,
        jScore,
        pScore);

    System.out.println("MBTI类型: " + mbtiType);
    // 返回最新保存的结果
    return resultMapper.getLatestResultByUsername(username);
  }

  @Override
  public List<MbtiResult> getUserTestRecords(String username) {
    return resultMapper.findByUsername(username);
  }

  @Override
  public boolean deleteTestRecord(Integer recordId) {
    return resultMapper.deleteById(recordId) > 0;
  }
}