package org.SETrain.CareerNavigator.Service.Impl;

import org.SETrain.CareerNavigator.Entity.*;
import org.SETrain.CareerNavigator.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserProfileServiceImpl implements UserProfileService {

  @Autowired
  private ResumeService resumeService;

  @Autowired
  private PersonalinfoService personalinfoService;

  @Autowired
  private EducationService educationService;

  @Autowired
  private ProjectsService projectsService;

  @Autowired
  private SkillsService skillsService;

  @Autowired
  private HonorsService honorsService;

  @Override
  @Cacheable(value = "userProfile", key = "#username")
  public Map<String, Object> getUserProfile(String username) {
    if (username == null) {
      throw new IllegalArgumentException("用户名不能为空");
    }

    // 1. 获取用户最新的简历ID
    Integer resumeId = resumeService.getLatestResumeIdByUsername(username);
    if (resumeId == null) {
      throw new RuntimeException("用户没有简历信息");
    }

    Map<String, Object> profile = new HashMap<>();

    try {
      // 2. 使用简历ID获取相关信息
      // 获取个人信息
      Personalinfo personalInfo = personalinfoService.getPersonalInfoByResumeId(resumeId);
      profile.put("personalInfo", personalInfo);

      // 获取教育经历
      List<Education> educationList = educationService.getEducationByResumeId(resumeId);
      profile.put("educationList", educationList);

      // 获取项目经历
      List<Projects> projectList = projectsService.getProjectsByResumeId(resumeId);
      profile.put("projectList", projectList);

      // 获取技能
      List<Skills> skillsList = skillsService.getSkillsByResumeId(resumeId);
      profile.put("skillsList", skillsList);

      // 获取荣誉
      List<Honors> honorList = honorsService.getHonorsByResumeId(resumeId);
      profile.put("honorList", honorList);

      // 提取关键词
      List<String> keywords = extractKeywords(personalInfo, educationList, projectList, honorList);
      profile.put("keywords", keywords);

      // 生成个人总结
      String summary = generateSummary(personalInfo, educationList, projectList, honorList);
      profile.put("summary", summary);

      // 分析性格特征
      Map<String, Double> personalityTraits = analyzePersonalityTraits(personalInfo, projectList);
      profile.put("personalityTraits", personalityTraits);

      // 分析职业兴趣
      Map<String, Double> careerInterests = analyzeCareerInterests(projectList, educationList);
      profile.put("careerInterests", careerInterests);

      // 评估能力
      Map<String, Double> competency = assessCompetency(projectList, educationList, honorList);
      profile.put("competency", competency);

      // 生成职业推荐
      List<String> recommendations = generateCareerRecommendations(careerInterests, competency);
      profile.put("recommendations", recommendations);

      // 分析技能差距
      Map<String, List<String>> skillGaps = analyzeSkillGaps(competency, recommendations);
      profile.put("skillGaps", skillGaps);

      // 评估市场价值
      Map<String, Double> marketValue = assessMarketValue(competency, projectList);
      profile.put("marketValue", marketValue);

      // 生成发展建议
      List<String> developmentSuggestions = generateDevelopmentSuggestions(skillGaps, marketValue);
      profile.put("developmentSuggestions", developmentSuggestions);

      // 分析行业趋势
      Map<String, Double> industryTrends = analyzeIndustryTrends(projectList, educationList);
      profile.put("industryTrends", industryTrends);

      // 规划学习路径
      Map<String, List<String>> learningPath = planLearningPath(skillGaps, industryTrends);
      profile.put("learningPath", learningPath);

      // 分析薪资期望
      Map<String, Double> salaryExpectation = analyzeSalaryExpectation(competency, marketValue);
      profile.put("salaryExpectation", salaryExpectation);

      // 分析职业目标
      Map<String, List<String>> careerGoals = analyzeCareerGoals(careerInterests, competency);
      profile.put("careerGoals", careerGoals);

      // 分析工作生活平衡
      Map<String, Double> workLifeBalance = analyzeWorkLifeBalance(personalInfo, projectList);
      profile.put("workLifeBalance", workLifeBalance);

      // 生成人脉拓展建议
      Map<String, List<String>> networkingSuggestions = generateNetworkingSuggestions(careerInterests, industryTrends);
      profile.put("networkingSuggestions", networkingSuggestions);

    } catch (Exception e) {
      throw new RuntimeException("生成用户画像失败: " + e.getMessage(), e);
    }

    return profile;
  }

  @Override
  public List<String> extractKeywords(Personalinfo personalInfo, List<Education> educationList,
      List<Projects> projectList, List<Honors> honorList) {
    if (personalInfo == null) {
      throw new IllegalArgumentException("个人信息不能为空");
    }

    Set<String> keywords = new HashSet<>();

    // 从个人信息中提取关键词
    if (StringUtils.hasText(personalInfo.getUniversity())) {
      keywords.add(personalInfo.getUniversity());
    }
    if (StringUtils.hasText(personalInfo.getMajor())) {
      keywords.add(personalInfo.getMajor());
    }
    if (StringUtils.hasText(personalInfo.getApplicationPosition())) {
      keywords.add(personalInfo.getApplicationPosition());
    }

    // 从教育经历中提取关键词
    if (educationList != null) {
      educationList.forEach(edu -> {
        if (StringUtils.hasText(edu.getSchool())) {
          keywords.add(edu.getSchool());
        }
        if (StringUtils.hasText(edu.getMajor())) {
          keywords.add(edu.getMajor());
        }
        if (StringUtils.hasText(edu.getDegree())) {
          keywords.add(edu.getDegree());
        }
      });
    }

    // 从项目经历中提取关键词
    if (projectList != null) {
      projectList.forEach(project -> {
        if (StringUtils.hasText(project.getProjectName())) {
          keywords.add(project.getProjectName());
        }
        if (StringUtils.hasText(project.getRole())) {
          keywords.add(project.getRole());
        }
        if (StringUtils.hasText(project.getDescription())) {
          keywords.addAll(Arrays.asList(project.getDescription().split("\\s+")));
        }
      });
    }

    // 从荣誉中提取关键词
    if (honorList != null) {
      honorList.forEach(honor -> {
        if (StringUtils.hasText(honor.getHonorName())) {
          keywords.add(honor.getHonorName());
        }
        if (StringUtils.hasText(honor.getDescription())) {
          keywords.addAll(Arrays.asList(honor.getDescription().split("\\s+")));
        }
      });
    }

    return new ArrayList<>(keywords);
  }

  @Override
  public String generateSummary(Personalinfo personalInfo, List<Education> educationList,
      List<Projects> projectList, List<Honors> honorList) {
    if (personalInfo == null) {
      throw new IllegalArgumentException("个人信息不能为空");
    }

    StringBuilder summary = new StringBuilder();

    // 添加基本信息
    if (StringUtils.hasText(personalInfo.getName())) {
      summary.append(personalInfo.getName());
    }
    if (personalInfo.getAge() != null) {
      summary.append("，").append(personalInfo.getAge()).append("岁");
    }
    if (StringUtils.hasText(personalInfo.getUniversity())) {
      summary.append("，").append(personalInfo.getUniversity()).append("毕业");
    }
    if (StringUtils.hasText(personalInfo.getMajor())) {
      summary.append("，专业：").append(personalInfo.getMajor());
    }
    summary.append("。");

    // 添加教育背景
    if (educationList != null && !educationList.isEmpty()) {
      summary.append("教育背景：");
      String educationStr = educationList.stream()
          .filter(edu -> StringUtils.hasText(edu.getSchool()) && StringUtils.hasText(edu.getDegree()))
          .map(edu -> edu.getSchool() + "(" + edu.getDegree() + ")")
          .collect(Collectors.joining("、"));
      if (!educationStr.isEmpty()) {
        summary.append(educationStr).append("。");
      }
    }

    // 添加项目经验
    if (projectList != null && !projectList.isEmpty()) {
      summary.append("项目经验：");
      String projectStr = projectList.stream()
          .filter(project -> StringUtils.hasText(project.getProjectName()) && StringUtils.hasText(project.getRole()))
          .map(project -> project.getProjectName() + "(" + project.getRole() + ")")
          .collect(Collectors.joining("、"));
      if (!projectStr.isEmpty()) {
        summary.append(projectStr).append("。");
      }
    }

    return summary.toString();
  }

  @Override
  public Map<String, Double> analyzePersonalityTraits(Personalinfo personalInfo, List<Projects> projectList) {
    Map<String, Double> traits = new HashMap<>();

    if (projectList != null) {
      int leadershipCount = 0;
      int teamworkCount = 0;
      int innovationCount = 0;
      int communicationCount = 0;
      int responsibilityCount = 0;

      for (Projects project : projectList) {
        String role = project.getRole().toLowerCase();
        String description = project.getDescription() != null ? project.getDescription().toLowerCase() : "";

        if (role.contains("leader") || role.contains("负责人") || role.contains("主管")) {
          leadershipCount++;
        }
        if (description.contains("team") || description.contains("团队") || description.contains("协作")) {
          teamworkCount++;
        }
        if (description.contains("innovate") || description.contains("创新") || description.contains("改进")) {
          innovationCount++;
        }
        if (description.contains("communicate") || description.contains("沟通") || description.contains("交流")) {
          communicationCount++;
        }
        if (description.contains("responsible") || description.contains("负责") || description.contains("职责")) {
          responsibilityCount++;
        }
      }

      int totalProjects = projectList.size();
      traits.put("领导力", calculateScore(leadershipCount, totalProjects));
      traits.put("团队协作", calculateScore(teamworkCount, totalProjects));
      traits.put("创新能力", calculateScore(innovationCount, totalProjects));
      traits.put("沟通能力", calculateScore(communicationCount, totalProjects));
      traits.put("责任心", calculateScore(responsibilityCount, totalProjects));
    }

    return traits;
  }

  @Override
  public Map<String, Double> analyzeCareerInterests(List<Projects> projectList, List<Education> educationList) {
    Map<String, Double> interests = new HashMap<>();

    if (projectList != null) {
      Map<String, Integer> interestCount = new HashMap<>();
      String[] techKeywords = {
          "java", "python", "web", "mobile", "data", "cloud", "ai",
          "前端", "后端", "全栈", "大数据", "人工智能", "机器学习",
          "区块链", "物联网", "安全", "测试", "运维"
      };

      for (Projects project : projectList) {
        String description = project.getDescription() != null ? project.getDescription().toLowerCase() : "";
        for (String keyword : techKeywords) {
          if (description.contains(keyword)) {
            interestCount.merge(keyword, 1, Integer::sum);
          }
        }
      }

      for (Map.Entry<String, Integer> entry : interestCount.entrySet()) {
        interests.put(entry.getKey(), calculateScore(entry.getValue(), projectList.size()));
      }
    }

    return interests;
  }

  @Override
  public Map<String, Double> assessCompetency(List<Projects> projectList, List<Education> educationList,
      List<Honors> honorList) {
    Map<String, Double> competency = new HashMap<>();

    if (projectList != null) {
      Map<String, Integer> skillCount = new HashMap<>();
      String[] skills = {
          "java", "python", "spring", "database", "frontend", "backend",
          "算法", "数据结构", "设计模式", "微服务", "分布式", "高并发",
          "性能优化", "安全", "测试", "运维", "项目管理"
      };

      for (Projects project : projectList) {
        String description = project.getDescription() != null ? project.getDescription().toLowerCase() : "";
        for (String skill : skills) {
          if (description.contains(skill)) {
            skillCount.merge(skill, 1, Integer::sum);
          }
        }
      }

      for (Map.Entry<String, Integer> entry : skillCount.entrySet()) {
        competency.put(entry.getKey(), calculateScore(entry.getValue(), projectList.size()));
      }
    }

    return competency;
  }

  @Override
  public List<String> generateCareerRecommendations(Map<String, Double> careerInterests,
      Map<String, Double> competencyAssessment) {
    List<String> recommendations = new ArrayList<>();

    if (careerInterests != null && competencyAssessment != null) {
      Map<String, Double> scores = new HashMap<>();

      for (Map.Entry<String, Double> interest : careerInterests.entrySet()) {
        double competencyScore = competencyAssessment.getOrDefault(interest.getKey(), 0.0);
        double totalScore = (interest.getValue() + competencyScore) / 2;
        scores.put(interest.getKey(), totalScore);
      }

      scores.entrySet().stream()
          .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
          .limit(3)
          .forEach(entry -> {
            recommendations.add(entry.getKey() + "开发工程师");
          });
    }

    return recommendations;
  }

  @Override
  public Map<String, List<String>> analyzeSkillGaps(Map<String, Double> competencyAssessment,
      List<String> targetPositions) {
    Map<String, List<String>> gaps = new HashMap<>();

    if (targetPositions != null && competencyAssessment != null) {
      for (String position : targetPositions) {
        List<String> requiredSkills = getRequiredSkills(position);
        List<String> missingSkills = new ArrayList<>();

        for (String skill : requiredSkills) {
          if (competencyAssessment.getOrDefault(skill, 0.0) < 0.5) {
            missingSkills.add(skill);
          }
        }

        gaps.put(position, missingSkills);
      }
    }

    return gaps;
  }

  @Override
  public Map<String, Double> assessMarketValue(Map<String, Double> competencyAssessment,
      List<Projects> projectList) {
    Map<String, Double> marketValue = new HashMap<>();

    if (competencyAssessment != null) {
      double totalScore = competencyAssessment.values().stream()
          .mapToDouble(Double::doubleValue)
          .average()
          .orElse(0.0);

      if (projectList != null) {
        double projectScore = projectList.size() * 0.1;
        totalScore = Math.min(1.0, totalScore + projectScore);
      }

      marketValue.put("初级", totalScore * 0.5);
      marketValue.put("中级", totalScore * 0.8);
      marketValue.put("高级", totalScore);
    }

    return marketValue;
  }

  @Override
  public List<String> generateDevelopmentSuggestions(Map<String, List<String>> skillGaps,
      Map<String, Double> marketValue) {
    List<String> suggestions = new ArrayList<>();

    if (skillGaps != null) {
      for (Map.Entry<String, List<String>> entry : skillGaps.entrySet()) {
        if (!entry.getValue().isEmpty()) {
          suggestions.add("建议提升" + entry.getKey() + "方向所需的技能：" +
              String.join("、", entry.getValue()));
        }
      }
    }

    if (marketValue != null) {
      double maxValue = marketValue.values().stream()
          .mapToDouble(Double::doubleValue)
          .max()
          .orElse(0.0);

      if (maxValue < 0.6) {
        suggestions.add("建议通过参与更多项目来提升市场竞争力");
      }
    }

    return suggestions;
  }

  @Override
  public Map<String, Double> analyzeIndustryTrends(List<Projects> projectList,
      List<Education> educationList) {
    Map<String, Double> trends = new HashMap<>();

    String[] techTrends = { "ai", "cloud", "bigdata", "blockchain", "iot" };
    for (String trend : techTrends) {
      int count = 0;
      if (projectList != null) {
        for (Projects project : projectList) {
          if (project.getDescription() != null &&
              project.getDescription().toLowerCase().contains(trend)) {
            count++;
          }
        }
      }
      trends.put(trend, (double) count / (projectList != null ? projectList.size() : 1));
    }

    return trends;
  }

  @Override
  public Map<String, List<String>> planLearningPath(Map<String, List<String>> skillGaps,
      Map<String, Double> industryTrends) {
    Map<String, List<String>> learningPath = new HashMap<>();

    if (skillGaps != null) {
      for (Map.Entry<String, List<String>> entry : skillGaps.entrySet()) {
        List<String> path = new ArrayList<>();
        for (String skill : entry.getValue()) {
          double trendScore = industryTrends != null ? industryTrends.getOrDefault(skill, 0.0) : 0.0;

          if (trendScore > 0.5) {
            path.add("优先学习：" + skill);
          } else {
            path.add("学习：" + skill);
          }
        }
        learningPath.put(entry.getKey(), path);
      }
    }

    return learningPath;
  }

  @Override
  public Map<String, Double> analyzeSalaryExpectation(Map<String, Double> competencyAssessment,
      Map<String, Double> marketValue) {
    Map<String, Double> salaryExpectation = new HashMap<>();

    if (competencyAssessment != null && marketValue != null) {
      double baseSalary = 10000.0;
      double competencyScore = competencyAssessment.values().stream()
          .mapToDouble(Double::doubleValue)
          .average()
          .orElse(0.0);

      double marketScore = marketValue.values().stream()
          .mapToDouble(Double::doubleValue)
          .max()
          .orElse(0.0);

      salaryExpectation.put("初级", baseSalary * (1 + competencyScore * 0.5));
      salaryExpectation.put("中级", baseSalary * (1 + competencyScore * 0.8));
      salaryExpectation.put("高级", baseSalary * (1 + competencyScore * marketScore));
    }

    return salaryExpectation;
  }

  @Override
  public Map<String, List<String>> analyzeCareerGoals(Map<String, Double> careerInterests,
      Map<String, Double> competencyAssessment) {
    Map<String, List<String>> goals = new HashMap<>();

    if (careerInterests != null && competencyAssessment != null) {
      for (Map.Entry<String, Double> interest : careerInterests.entrySet()) {
        List<String> goalList = new ArrayList<>();
        double competencyScore = competencyAssessment.getOrDefault(interest.getKey(), 0.0);

        if (competencyScore < 0.3) {
          goalList.add("短期目标：掌握" + interest.getKey() + "基础知识");
          goalList.add("中期目标：参与" + interest.getKey() + "相关项目");
          goalList.add("长期目标：成为" + interest.getKey() + "领域专家");
        } else if (competencyScore < 0.7) {
          goalList.add("短期目标：提升" + interest.getKey() + "项目经验");
          goalList.add("中期目标：成为" + interest.getKey() + "高级工程师");
          goalList.add("长期目标：担任" + interest.getKey() + "技术负责人");
        } else {
          goalList.add("短期目标：深化" + interest.getKey() + "技术深度");
          goalList.add("中期目标：成为" + interest.getKey() + "架构师");
          goalList.add("长期目标：成为" + interest.getKey() + "技术专家");
        }

        goals.put(interest.getKey(), goalList);
      }
    }

    return goals;
  }

  @Override
  public Map<String, Double> analyzeWorkLifeBalance(Personalinfo personalInfo, List<Projects> projectList) {
    Map<String, Double> balance = new HashMap<>();

    if (projectList != null) {
      int workIntensity = 0;
      for (Projects project : projectList) {
        if (project.getDescription() != null) {
          if (project.getDescription().toLowerCase().contains("加班")) {
            workIntensity++;
          }
        }
      }

      double balanceScore = 1.0 - ((double) workIntensity / projectList.size());
      balance.put("工作强度", balanceScore);
      balance.put("生活平衡", balanceScore);
    }

    return balance;
  }

  @Override
  public Map<String, List<String>> generateNetworkingSuggestions(Map<String, Double> careerInterests,
      Map<String, Double> industryTrends) {
    Map<String, List<String>> suggestions = new HashMap<>();

    if (careerInterests != null) {
      for (Map.Entry<String, Double> interest : careerInterests.entrySet()) {
        List<String> suggestionList = new ArrayList<>();

        double trendScore = industryTrends != null ? industryTrends.getOrDefault(interest.getKey(), 0.0) : 0.0;

        suggestionList.add("参加" + interest.getKey() + "技术社区活动");
        suggestionList.add("关注" + interest.getKey() + "领域的技术博客");

        if (trendScore > 0.5) {
          suggestionList.add("参与" + interest.getKey() + "相关的开源项目");
          suggestionList.add("参加" + interest.getKey() + "技术大会");
        }

        suggestions.put(interest.getKey(), suggestionList);
      }
    }

    return suggestions;
  }

  // 辅助方法：获取特定职位所需的技能
  private List<String> getRequiredSkills(String position) {
    if (!StringUtils.hasText(position)) {
      return new ArrayList<>();
    }

    Map<String, List<String>> positionSkills = new HashMap<>();
    positionSkills.put("java开发工程师", Arrays.asList("java", "spring", "database", "frontend"));
    positionSkills.put("python开发工程师", Arrays.asList("python", "data", "ai", "database"));
    positionSkills.put("前端开发工程师", Arrays.asList("frontend", "javascript", "html", "css"));
    positionSkills.put("后端开发工程师", Arrays.asList("java", "python", "database", "api"));

    return positionSkills.getOrDefault(position, new ArrayList<>());
  }

  // 辅助方法：计算得分
  private double calculateScore(int count, int total) {
    if (total <= 0) {
      return 0.0;
    }
    double score = (double) count / total;
    // 使用sigmoid函数进行归一化
    return 1 / (1 + Math.exp(-5 * (score - 0.5)));
  }
}
