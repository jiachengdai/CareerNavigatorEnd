package org.SETrain.CareerNavigator.Controller;

import org.SETrain.CareerNavigator.Entity.*;
import org.SETrain.CareerNavigator.Service.UserProfileService;
import org.SETrain.CareerNavigator.DTO.UserProfileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.Map;

@Tag(name = "用户画像管理")
@RestController
@RequestMapping("/userprofile")
public class UserProfileController {

  @Autowired
  private UserProfileService userProfileService;

  @Operation(summary = "获取用户画像数据")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "获取成功"),
      @ApiResponse(responseCode = "400", description = "获取失败")
  })
  @GetMapping("/{userId}")
  public Map<String, Object> getUserProfile(@Parameter(description = "用户ID") @PathVariable Integer userId) {
    return userProfileService.getUserProfile(userId);
  }

  @Operation(summary = "提取用户画像关键词")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "提取成功"),
      @ApiResponse(responseCode = "400", description = "提取失败")
  })
  @PostMapping("/{userId}/keywords")
  public List<String> extractKeywords(
      @Parameter(description = "用户ID") @PathVariable Integer userId,
      @Parameter(description = "用户画像请求数据") @RequestBody UserProfileRequest request) {
    return userProfileService.extractKeywords(
        request.getPersonalInfo(),
        request.getEducationList(),
        request.getProjectList(),
        request.getHonorList());
  }

  @Operation(summary = "生成个人总结")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "生成成功"),
      @ApiResponse(responseCode = "400", description = "生成失败")
  })
  @PostMapping("/{userId}/summary")
  public String generateSummary(
      @Parameter(description = "用户ID") @PathVariable Integer userId,
      @Parameter(description = "用户画像请求数据") @RequestBody UserProfileRequest request) {
    return userProfileService.generateSummary(
        request.getPersonalInfo(),
        request.getEducationList(),
        request.getProjectList(),
        request.getHonorList());
  }

  @Operation(summary = "分析性格特征")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "分析成功"),
      @ApiResponse(responseCode = "400", description = "分析失败")
  })
  @PostMapping("/{userId}/personality")
  public Map<String, Double> analyzePersonality(
      @Parameter(description = "用户ID") @PathVariable Integer userId,
      @Parameter(description = "用户画像请求数据") @RequestBody UserProfileRequest request) {
    return userProfileService.analyzePersonalityTraits(
        request.getPersonalInfo(),
        request.getProjectList());
  }

  @Operation(summary = "分析职业兴趣")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "分析成功"),
      @ApiResponse(responseCode = "400", description = "分析失败")
  })
  @PostMapping("/{userId}/interests")
  public Map<String, Double> analyzeInterests(
      @Parameter(description = "用户ID") @PathVariable Integer userId,
      @Parameter(description = "用户画像请求数据") @RequestBody UserProfileRequest request) {
    return userProfileService.analyzeCareerInterests(
        request.getProjectList(),
        request.getEducationList());
  }

  @Operation(summary = "评估能力")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "评估成功"),
      @ApiResponse(responseCode = "400", description = "评估失败")
  })
  @PostMapping("/{userId}/competency")
  public Map<String, Double> assessCompetency(
      @Parameter(description = "用户ID") @PathVariable Integer userId,
      @Parameter(description = "用户画像请求数据") @RequestBody UserProfileRequest request) {
    return userProfileService.assessCompetency(
        request.getProjectList(),
        request.getEducationList(),
        request.getHonorList());
  }

  @Operation(summary = "生成职业推荐")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "生成成功"),
      @ApiResponse(responseCode = "400", description = "生成失败")
  })
  @PostMapping("/{userId}/recommendations")
  public List<String> generateRecommendations(
      @Parameter(description = "用户ID") @PathVariable Integer userId,
      @Parameter(description = "用户画像请求数据") @RequestBody UserProfileRequest request) {
    return userProfileService.generateCareerRecommendations(
        request.getCareerInterests(),
        request.getCompetencyAssessment());
  }

  @Operation(summary = "分析技能差距")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "分析成功"),
      @ApiResponse(responseCode = "400", description = "分析失败")
  })
  @PostMapping("/{userId}/skill-gaps")
  public Map<String, List<String>> analyzeSkillGaps(
      @Parameter(description = "用户ID") @PathVariable Integer userId,
      @Parameter(description = "用户画像请求数据") @RequestBody UserProfileRequest request) {
    return userProfileService.analyzeSkillGaps(
        request.getCompetencyAssessment(),
        request.getTargetPositions());
  }

  @Operation(summary = "评估市场价值")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "评估成功"),
      @ApiResponse(responseCode = "400", description = "评估失败")
  })
  @PostMapping("/{userId}/market-value")
  public Map<String, Double> assessMarketValue(
      @Parameter(description = "用户ID") @PathVariable Integer userId,
      @Parameter(description = "用户画像请求数据") @RequestBody UserProfileRequest request) {
    return userProfileService.assessMarketValue(
        request.getCompetencyAssessment(),
        request.getProjectList());
  }

  @Operation(summary = "生成发展建议")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "生成成功"),
      @ApiResponse(responseCode = "400", description = "生成失败")
  })
  @PostMapping("/{userId}/development-suggestions")
  public List<String> generateDevelopmentSuggestions(
      @Parameter(description = "用户ID") @PathVariable Integer userId,
      @Parameter(description = "用户画像请求数据") @RequestBody UserProfileRequest request) {
    return userProfileService.generateDevelopmentSuggestions(
        request.getSkillGaps(),
        request.getMarketValue());
  }

  @Operation(summary = "分析行业趋势")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "分析成功"),
      @ApiResponse(responseCode = "400", description = "分析失败")
  })
  @PostMapping("/{userId}/industry-trends")
  public Map<String, Double> analyzeIndustryTrends(
      @Parameter(description = "用户ID") @PathVariable Integer userId,
      @Parameter(description = "用户画像请求数据") @RequestBody UserProfileRequest request) {
    return userProfileService.analyzeIndustryTrends(
        request.getProjectList(),
        request.getEducationList());
  }

  @Operation(summary = "规划学习路径")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "规划成功"),
      @ApiResponse(responseCode = "400", description = "规划失败")
  })
  @PostMapping("/{userId}/learning-path")
  public Map<String, List<String>> planLearningPath(
      @Parameter(description = "用户ID") @PathVariable Integer userId,
      @Parameter(description = "用户画像请求数据") @RequestBody UserProfileRequest request) {
    return userProfileService.planLearningPath(
        request.getSkillGaps(),
        request.getIndustryTrends());
  }

  @Operation(summary = "分析薪资期望")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "分析成功"),
      @ApiResponse(responseCode = "400", description = "分析失败")
  })
  @PostMapping("/{userId}/salary-expectation")
  public Map<String, Double> analyzeSalaryExpectation(
      @Parameter(description = "用户ID") @PathVariable Integer userId,
      @Parameter(description = "用户画像请求数据") @RequestBody UserProfileRequest request) {
    return userProfileService.analyzeSalaryExpectation(
        request.getCompetencyAssessment(),
        request.getMarketValue());
  }

  @Operation(summary = "分析职业目标")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "分析成功"),
      @ApiResponse(responseCode = "400", description = "分析失败")
  })
  @PostMapping("/{userId}/career-goals")
  public Map<String, List<String>> analyzeCareerGoals(
      @Parameter(description = "用户ID") @PathVariable Integer userId,
      @Parameter(description = "用户画像请求数据") @RequestBody UserProfileRequest request) {
    return userProfileService.analyzeCareerGoals(
        request.getCareerInterests(),
        request.getCompetencyAssessment());
  }

  @Operation(summary = "分析工作生活平衡")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "分析成功"),
      @ApiResponse(responseCode = "400", description = "分析失败")
  })
  @PostMapping("/{userId}/work-life-balance")
  public Map<String, Double> analyzeWorkLifeBalance(
      @Parameter(description = "用户ID") @PathVariable Integer userId,
      @Parameter(description = "用户画像请求数据") @RequestBody UserProfileRequest request) {
    return userProfileService.analyzeWorkLifeBalance(
        request.getPersonalInfo(),
        request.getProjectList());
  }

  @Operation(summary = "生成人脉拓展建议")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "生成成功"),
      @ApiResponse(responseCode = "400", description = "生成失败")
  })
  @PostMapping("/{userId}/networking-suggestions")
  public Map<String, List<String>> generateNetworkingSuggestions(
      @Parameter(description = "用户ID") @PathVariable Integer userId,
      @Parameter(description = "用户画像请求数据") @RequestBody UserProfileRequest request) {
    return userProfileService.generateNetworkingSuggestions(
        request.getCareerInterests(),
        request.getIndustryTrends());
  }
}