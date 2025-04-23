package org.SETrain.CareerNavigator.Entity;

import java.util.List;
import java.util.Map;

public class UserProfile {
  private Personalinfo personalInfo;
  private List<Education> educationList;
  private List<Projects> projectList;
  private List<Honors> honorList;
  private Map<String, Integer> skillMap; // 技能及其熟练度
  private String avatarUrl;
  private String summary; // 个人总结
  private List<String> keywords; // 关键词列表

  // 新增分析维度
  private Map<String, Double> personalityTraits; // 性格特征分析
  private Map<String, Double> careerInterests; // 职业兴趣分析
  private Map<String, Double> competencyAssessment; // 能力评估
  private List<String> careerRecommendations; // 职业推荐
  private Map<String, List<String>> skillGaps; // 技能差距分析
  private Map<String, Double> marketValue; // 市场价值评估
  private List<String> developmentSuggestions; // 发展建议
  private Map<String, Double> industryTrends; // 行业趋势分析
  private Map<String, List<String>> learningPath; // 学习路径规划
  private Map<String, Double> salaryExpectation; // 薪资期望分析
  private Map<String, List<String>> careerGoals; // 职业目标分析
  private Map<String, Double> workLifeBalance; // 工作生活平衡分析
  private Map<String, List<String>> networkingSuggestions; // 人脉拓展建议

  // Getters and Setters
  public Personalinfo getPersonalInfo() {
    return personalInfo;
  }

  public void setPersonalInfo(Personalinfo personalInfo) {
    this.personalInfo = personalInfo;
  }

  public List<Education> getEducationList() {
    return educationList;
  }

  public void setEducationList(List<Education> educationList) {
    this.educationList = educationList;
  }

  public List<Projects> getProjectList() {
    return projectList;
  }

  public void setProjectList(List<Projects> projectList) {
    this.projectList = projectList;
  }

  public List<Honors> getHonorList() {
    return honorList;
  }

  public void setHonorList(List<Honors> honorList) {
    this.honorList = honorList;
  }

  public Map<String, Integer> getSkillMap() {
    return skillMap;
  }

  public void setSkillMap(Map<String, Integer> skillMap) {
    this.skillMap = skillMap;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public List<String> getKeywords() {
    return keywords;
  }

  public void setKeywords(List<String> keywords) {
    this.keywords = keywords;
  }

  public Map<String, Double> getPersonalityTraits() {
    return personalityTraits;
  }

  public void setPersonalityTraits(Map<String, Double> personalityTraits) {
    this.personalityTraits = personalityTraits;
  }

  public Map<String, Double> getCareerInterests() {
    return careerInterests;
  }

  public void setCareerInterests(Map<String, Double> careerInterests) {
    this.careerInterests = careerInterests;
  }

  public Map<String, Double> getCompetencyAssessment() {
    return competencyAssessment;
  }

  public void setCompetencyAssessment(Map<String, Double> competencyAssessment) {
    this.competencyAssessment = competencyAssessment;
  }

  public List<String> getCareerRecommendations() {
    return careerRecommendations;
  }

  public void setCareerRecommendations(List<String> careerRecommendations) {
    this.careerRecommendations = careerRecommendations;
  }

  public Map<String, List<String>> getSkillGaps() {
    return skillGaps;
  }

  public void setSkillGaps(Map<String, List<String>> skillGaps) {
    this.skillGaps = skillGaps;
  }

  public Map<String, Double> getMarketValue() {
    return marketValue;
  }

  public void setMarketValue(Map<String, Double> marketValue) {
    this.marketValue = marketValue;
  }

  public List<String> getDevelopmentSuggestions() {
    return developmentSuggestions;
  }

  public void setDevelopmentSuggestions(List<String> developmentSuggestions) {
    this.developmentSuggestions = developmentSuggestions;
  }

  public Map<String, Double> getIndustryTrends() {
    return industryTrends;
  }

  public void setIndustryTrends(Map<String, Double> industryTrends) {
    this.industryTrends = industryTrends;
  }

  public Map<String, List<String>> getLearningPath() {
    return learningPath;
  }

  public void setLearningPath(Map<String, List<String>> learningPath) {
    this.learningPath = learningPath;
  }

  public Map<String, Double> getSalaryExpectation() {
    return salaryExpectation;
  }

  public void setSalaryExpectation(Map<String, Double> salaryExpectation) {
    this.salaryExpectation = salaryExpectation;
  }

  public Map<String, List<String>> getCareerGoals() {
    return careerGoals;
  }

  public void setCareerGoals(Map<String, List<String>> careerGoals) {
    this.careerGoals = careerGoals;
  }

  public Map<String, Double> getWorkLifeBalance() {
    return workLifeBalance;
  }

  public void setWorkLifeBalance(Map<String, Double> workLifeBalance) {
    this.workLifeBalance = workLifeBalance;
  }

  public Map<String, List<String>> getNetworkingSuggestions() {
    return networkingSuggestions;
  }

  public void setNetworkingSuggestions(Map<String, List<String>> networkingSuggestions) {
    this.networkingSuggestions = networkingSuggestions;
  }
}