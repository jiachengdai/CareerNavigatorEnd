package org.SETrain.CareerNavigator.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.SETrain.CareerNavigator.Entity.*;
import org.SETrain.CareerNavigator.Service.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResumeSummaryServiceImpl implements ResumeSummaryService {

    private final ResumeService resumeService;
    private final PersonalinfoService personalinfoService;
    private final EducationService educationService;
    private final ProjectsService projectsService;
    private final SkillsService skillsService;
    private final HonorsService honorsService;
    private final WorkexperienceService workexperienceService;

    @Override
    public String getResumeSummary(String username) {
        // 获取简历ID
        Resume resume = resumeService.getResumeByUsername(username);
        if (resume == null) {
            throw new RuntimeException("未找到用户简历");
        }
        Integer resumeId = resume.getId();

        // 使用简历ID获取相关信息
        Map<String, Object> profile = new HashMap<>();

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

        // 获取工作经验
        List<Workexperience> workList = workexperienceService.getWorkexperienceByResumeId(resumeId);
        profile.put("workList", workList);


        return profile.toString();
    }
}
