package org.SETrain.CareerNavigator.Service;

import org.SETrain.CareerNavigator.Entity.*;
import java.util.List;
import java.util.Map;

public interface UserProfileService {
        // 获取用户画像数据
        Map<String, Object> getUserProfile(String username);

        // 提取关键词
        List<String> extractKeywords(Personalinfo personalInfo, List<Education> educationList,
                        List<Projects> projectList, List<Honors> honorList);

        // 生成个人总结
        String generateSummary(Personalinfo personalInfo, List<Education> educationList,
                        List<Projects> projectList, List<Honors> honorList);

        // 分析性格特征
        Map<String, Double> analyzePersonalityTraits(Personalinfo personalInfo, List<Projects> projectList);

        // 分析职业兴趣
        Map<String, Double> analyzeCareerInterests(List<Projects> projectList, List<Education> educationList);

        // 评估能力
        Map<String, Double> assessCompetency(List<Projects> projectList, List<Education> educationList,
                        List<Honors> honorList);

        // 生成职业推荐
        List<String> generateCareerRecommendations(Map<String, Double> careerInterests,
                        Map<String, Double> competencyAssessment);

        // 分析技能差距
        Map<String, List<String>> analyzeSkillGaps(Map<String, Double> competencyAssessment,
                        List<String> targetPositions);

        // 评估市场价值
        Map<String, Double> assessMarketValue(Map<String, Double> competencyAssessment, List<Projects> projectList);

        // 生成发展建议
        List<String> generateDevelopmentSuggestions(Map<String, List<String>> skillGaps,
                        Map<String, Double> marketValue);

        // 分析行业趋势
        Map<String, Double> analyzeIndustryTrends(List<Projects> projectList, List<Education> educationList);

        // 规划学习路径
        Map<String, List<String>> planLearningPath(Map<String, List<String>> skillGaps,
                        Map<String, Double> industryTrends);

        // 分析薪资期望
        Map<String, Double> analyzeSalaryExpectation(Map<String, Double> competencyAssessment,
                        Map<String, Double> marketValue);

        // 分析职业目标
        Map<String, List<String>> analyzeCareerGoals(Map<String, Double> careerInterests,
                        Map<String, Double> competencyAssessment);

        // 分析工作生活平衡
        Map<String, Double> analyzeWorkLifeBalance(Personalinfo personalInfo, List<Projects> projectList);

        // 生成人脉拓展建议
        Map<String, List<String>> generateNetworkingSuggestions(Map<String, Double> careerInterests,
                        Map<String, Double> industryTrends);
}