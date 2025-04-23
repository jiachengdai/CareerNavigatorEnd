package org.SETrain.CareerNavigator.DTO;

import org.SETrain.CareerNavigator.Entity.*;

import java.util.List;
import java.util.Map;

public class UserProfileRequest {
  private Personalinfo personalInfo;
  private List<Education> educationList;
  private List<Projects> projectList;
  private List<Honors> honorList;
  private Map<String, Double> careerInterests;
  private Map<String, Double> competencyAssessment;
  private List<String> targetPositions;
  private Map<String, List<String>> skillGaps;
  private Map<String, Double> marketValue;
  private Map<String, Double> industryTrends;

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

  public List<String> getTargetPositions() {
    return targetPositions;
  }

  public void setTargetPositions(List<String> targetPositions) {
    this.targetPositions = targetPositions;
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

  public Map<String, Double> getIndustryTrends() {
    return industryTrends;
  }

  public void setIndustryTrends(Map<String, Double> industryTrends) {
    this.industryTrends = industryTrends;
  }
}