package org.SETrain.CareerNavigator.Controller;

import lombok.RequiredArgsConstructor;
import org.SETrain.CareerNavigator.Entity.Result;
import org.SETrain.CareerNavigator.Service.ResumeSummaryService;
import org.springframework.ai.chat.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai/career")
@RequiredArgsConstructor
public class CareerAnalysisController {

    @Autowired
    private  ChatClient chatClient;
    @Autowired
    private  ResumeSummaryService resumeSummaryService;

    @GetMapping("/profile/{username}")
    public Result  analyzeUserProfile(
            @PathVariable String username  ) {
        try {
            String profilePrompt = String.format("""
  请根据以下用户的简历信息，生成一份详尽而结构清晰的用户画像，输出为JSON格式，内容将用于前端页面的图文展示，请使内容语义自然、层次分明，适合用户浏览阅读：
  一些分析建议:以对话的口吻，尽量采用轻松、鼓励的语气，让内容显得更具人情味和鼓励性。
  对于用户未提及的证书或其他经验，建议以积极向上的语气表达，鼓励用户继续努力，避免任何负面的描述。
  例如：'目前没有相关证书，但这正是你未来发展的机会，加油！'，'还没有涉及某项技能，但你有很大的潜力去学习，继续加油！'。对于已提及的内容，可以强调优点并提出成长建议，鼓励用户保持进步。
  请严格返回以下结构的 JSON，对每一项字段务必有实际内容：
  {
    "basicInfo": {
      "name": "姓名",
      "age": 年龄（如未知可估算或标为null）,
      "education": "最高学历",
      "yearsOfExperience": 工作年限,
      "currentPosition": "当前/最近职位",
      "location": "工作城市"
    },
    "personality": {
      "traits": ["简洁描述的特质，如'好奇心强'"],
      "strengths": ["例如'学习能力强，快速适应新技术环境'"],
      "weaknesses": ["例如'表达略显内向'，请避免负面贬义"],
      "analysis": "结合简历内容进行性格分析，语言自然、有逻辑"
    },
    "professionalBackground": {
      "industries": ["主要行业，如'互联网'、'金融科技'"],
      "roles": ["典型岗位角色，如'后端开发工程师'"],
      "keyProjects": [
        {
          "name": "项目名称",
          "role": "项目角色",
          "achievements": ["突出成果1", "成果2，可带量化数据"]
        }
      ],
      "analysis": "对职业背景进行总结，适度强调经验亮点"
    },
    "skills": {
      "technical": [
        {
          "name": "技能名称",
          "level": 0.0-1.0 之间的熟练度（建议浮点）,
          "years": 使用时长,
          "certifications": ["相关证书名称，如有"]
        }
      ],
      "skillsummary": [
         {
           "dimension": "提炼出5个维度，便于画雷达图",
           "level": 0.0-1.0,
           "comment": "基于简历中的技能，展示用户在这些维度的能力水平"
         }
      ],
      "soft": [
        {
          "name": "沟通能力 / 团队协作等5个维度",
          "level": 0.0-1.0,
          "examples": ["举例说明，如'曾在团队中主持跨部门沟通'"]
        }
      ],
      "analysis": "综合技能进行分析评价，重点强调匹配职业目标的能力"
    },
    "careerPreferences": {
      "preferredRoles": ["如'后端开发'、'AI应用工程师'"],
      "preferredIndustries": ["如'人工智能'、'教育科技'"],
      "workStyle": ["偏好远程/团队协作/独立项目等"],
      "analysis": "根据简历与技能推测职业偏好，语气积极"
    },
    "developmentAreas": {
      "immediate": [
        {
          "area": "例如'系统设计能力'",
          "priority": "高/中/低",
          "suggestions": ["建议举措1", "具体行动建议2"]
        }
      ],
      "longTerm": [
        {
          "area": "例如'算法建模能力'",
          "timeline": "如'未来1-2年'",
          "suggestions": ["长远建议1", "建议2"]
        }
      ]
    },
    "marketPosition": {
      "currentLevel": "初级/中级/高级（参考经验）",
      "salaryRange": {"min": 最低月薪, "max": 最高月薪},
      "competitiveAdvantages": ["如'项目经验丰富，适应性强'"],
      "marketDemand": "简要分析当前市场对该用户背景的需求，例如'CV方向中高级人才紧缺，AI项目经验尤为加分'"
    }
  }
  用户简历信息：
  若简历中缺乏某项信息，请标为“未提及”，切勿凭空推断。
  %s
  """, resumeSummaryService.getResumeSummary(username));
        String res=chatClient.call(profilePrompt);
            return Result.success(res);

        } catch (Exception e) {
            return Result.error("用户画像分析失败: " + e.getMessage());
        }
    }

    @GetMapping("/analysis/{username}")
    public Result analyzeCareer(
            @PathVariable String username ) {
        try {
            String analysisPrompt = String.format("""
              请根据以下用户的简历信息，生成一份详细的个性化成长路径和职业规划建议。请以JSON格式返回，包含以下字段，并根据用户的背景和兴趣为每一部分提供独特的亮点：
              {
                  "careerPath": {
                      "shortTerm": {
                          "goals": ["目标1", "目标2"],
                          "timeline": "3-6个月",
                          "suggestions": ["建议1", "建议2"],
                          "requiredSkills": ["技能1", "技能2"],
                          "actionableSteps": ["具体行动项1", "具体行动项2"]
                      },
                      "mediumTerm": {
                          "goals": ["目标1", "目标2"],
                          "timeline": "1-2年",
                          "suggestions": ["建议1", "建议2"],
                          "requiredSkills": ["技能1", "技能2"],
                          "actionableSteps": ["具体行动项1", "具体行动项2"]
                      },
                      "longTerm": {
                          "goals": ["目标1", "目标2"],
                          "timeline": "3-5年",
                          "suggestions": ["建议1", "建议2"],
                          "requiredSkills": ["技能1", "技能2"],
                          "actionableSteps": ["具体行动项1", "具体行动项2"]
                      },
                      "futureVision": {
                          "longTermImpact": "基于当前技能，用户将在未来的技术/行业变革中扮演的角色。",
                          "disruptiveTrends": ["趋势1", "趋势2"],
                          "futureSkills": ["技能1", "技能2"],
                          "crossDomainOpportunities": ["跨领域机会1", "跨领域机会2"]
                      }
                  },
                  "learningPath": {
                      "immediate": [
                          {
                              "skill": "技能名称",
                              "resources": ["资源1", "资源2"],
                              "estimatedTime": "预计时间",
                              "learningStyle": "推荐学习方式：在线课程/自学/实践"
                          }
                      ],
                      "next3Months": [
                          {
                              "skill": "技能名称",
                              "resources": ["资源1", "资源2"],
                              "estimatedTime": "预计时间",
                              "learningStyle": "推荐学习方式：项目驱动学习/视频教程"
                          }
                      ],
                      "next6Months": [
                          {
                              "skill": "技能名称",
                              "resources": ["资源1", "资源2"],
                              "estimatedTime": "预计时间",
                              "learningStyle": "推荐学习方式：交互式学习/社区参与"
                          }
                      ]
                  },
                  "opportunities": {
                      "internal": [
                          {
                              "type": "潜在晋升机会",
                              "description": "根据用户的技能和当前职位，内部晋升路径的机会",
                              "requirements": ["要求1", "要求2"],
                              "actionItems": ["行动项1", "行动项2"]
                          }
                      ],
                      "external": [
                          {
                              "type": "行业跨界机会",
                              "description": "基于用户的技能和兴趣，探索跨行业转型的可能性",
                              "requirements": ["要求1", "要求2"],
                              "actionItems": ["行动项1", "行动项2"]
                          }
                      ]
                  },
                  "riskAnalysis": {
                      "potentialRisks": [
                          {
                              "risk": "当前技能可能会被新兴技术替代",
                              "impact": "高",
                              "mitigation": ["主动学习前沿技术", "参与行业内创新项目"]
                          }
                      ],
                      "marketTrends": ["技术变革", "行业重塑"],
                      "recommendations": ["建议1", "建议2", "建议3"]
                  },
                  "personalBranding": {
                      "strategy": "建立个人品牌的建议，包括社交媒体的使用、公开演讲的机会等。",
                      "publicSpeakingOpportunities": ["会议演讲", "行业活动参与"],
                      "personalBlog/Portfolio": "建议用户建立个人博客或在线作品集，展示个人技能与项目经历"
                  },
                  "workLifeBalance": {
                      "timeEnergyManagement": {
                          "suggestions": [
                              "合理安排工作与生活时间，避免职业倦怠。",
                              "使用时间管理工具如Trello、Notion，帮助规划任务。",
                              "学习如何调整精力，避免过度工作。"
                          ],
                          "methods": [
                              "使用时间管理工具安排每日任务和项目进度。",
                              "设定工作与休息时间，确保高效工作与充足休息。"
                          ]
                      },
                      "mentalHealthAndStressManagement": {
                          "suggestions": [
                              "定期冥想，调整心态，减少职业压力。",
                              "通过心理健康检查保持良好的精神状态。"
                          ],
                          "methods": [
                              "每天进行10-15分钟冥想，帮助减轻压力。",
                              "规划并享受定期休息日，保持身心健康。"
                          ]
                      }
                  },
                  "evaluationAndFeedback": {
                      "monthlyQuarterlyReviews": {
                          "suggestions": [
                              "定期回顾职业目标，评估进展情况。",
                              "根据反馈调整策略，确保目标达成。"
                          ],
                          "methods": [
                              "每月/月度自评，记录工作成效并调整策略。",
                              "季度回顾，分析目标进展与潜在调整。"
                          ]
                      },
                      "selfReflection": {
                          "suggestions": [
                              "定期自我反思，记录成长和不足。",
                              "根据反思结果调整职业目标，保持动态规划。"
                          ],
                          "methods": [
                              "每天记录工作中的得失，帮助了解自己成长。",
                              "每季度进行深度自我反馈，评估成功与挑战。"
                          ]
                      }
                  }
              }
              用户信息：
              %s
              """, resumeSummaryService.getResumeSummary(username));

            String response= chatClient.call(analysisPrompt) ;
            return Result.success(response);
        } catch (Exception e) {
            return Result.error("职业分析失败: " + e.getMessage());
        }
    }
}
