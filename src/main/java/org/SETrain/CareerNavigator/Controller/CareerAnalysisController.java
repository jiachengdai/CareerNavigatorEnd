package org.SETrain.CareerNavigator.Controller;

import lombok.RequiredArgsConstructor;
import org.SETrain.CareerNavigator.Entity.Result;
import org.SETrain.CareerNavigator.Service.ResumeSummaryService;
import org.SETrain.CareerNavigator.Util.ThreadLocalUtil;
import org.springframework.ai.chat.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;

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

    @GetMapping("/analysis")
    public Result analyzeCareer(
             @RequestParam String targetJob ,String description) {
        try {
            Map<String, Object> claims = ThreadLocalUtil.get();
            String username = (String) claims.get("username");
            String analysisPrompt = String.format("""
              请根据以下用户的简历信息，生成一份详细的个性化成长路径和职业规划建议。请以JSON格式返回，包含以下字段，并根据用户的背景和兴趣为每一部分提供独特的亮点。他的职业目标岗位是%s,这份岗位的需求是%s：
              { 
                      "shortTerm":  "短期计划"
                      "mediumTerm":  "中期计划"
                      "longTerm":  "长期计划”
                      "futureVision":  "职业未来发展"
                      "learningPath":  "学习路径及学习资源"
                      "riskAnalysis":  "风险"
                   
              }
              用户信息：
              %s
              """, targetJob,description,resumeSummaryService.getResumeSummary(username));

            String response= chatClient.call(analysisPrompt) ;
            return Result.success(response);
        } catch (Exception e) {
            return Result.error("职业分析失败: " + e.getMessage());
        }
    }
}
