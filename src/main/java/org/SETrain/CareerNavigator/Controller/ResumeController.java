package org.SETrain.CareerNavigator.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.SETrain.CareerNavigator.Entity.Result;
import org.SETrain.CareerNavigator.Entity.Resume;
import org.SETrain.CareerNavigator.Entity.ResumeAssessmentRecord;
import org.SETrain.CareerNavigator.Service.ResumeAssessmentRecordService;
import org.SETrain.CareerNavigator.Service.ResumeService;
import org.SETrain.CareerNavigator.Util.ThreadLocalUtil;
import org.springframework.ai.chat.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Autowired
    private  ResumeService resumeService;
    @Autowired
    private  ResumeAssessmentRecordService recordService;
    @Autowired
    private ChatClient chatClient;

    public ResumeController(ResumeService resumeService, ResumeAssessmentRecordService recordService) {
        this.resumeService = resumeService;
        this.recordService = recordService;
    }

    @Operation(summary = "简历评估")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "评估成功"),
        @ApiResponse(responseCode = "500", description = "AI服务异常或处理错误")
    })
    @PostMapping("/advanced-assess")
    public Result assessResume(@RequestParam Integer resumeId) {
        try {

            Resume resume=resumeService.getResumeById(resumeId);

            // Step 3: Perform the AI assessment on the resume content
            String promptPre="你是一个专业HR，请从专业技能匹配度、项目经验、教育背景、格式规范等维度进行简历评估，返回JSON格式：{score: 0-100, analysis: string, suggestions: string[]}。下面是简历的内容：";
            String jsonResult = chatClient.call(promptPre+resume.getContent()); // Or use savedResume.getContent()

            // Step 4: Create the assessment record using the actual resumeId
            // The constructor for ResumeAssessmentRecord is (Integer resumeId, String assessmentResult, Date assessmentTime)
            // The ResumeAssessmentRecordServiceImpl will set the assessmentTime if null is passed.
            ResumeAssessmentRecord record = new ResumeAssessmentRecord(resumeId, jsonResult, null);
            recordService.createRecord(record);

            return Result.success(jsonResult);
        } catch (Exception e) {
            // Log the exception e.printStackTrace(); or use a logger
            return Result.error("Error during advanced resume assessment: " + e.getMessage());
        }
    }

    // Other existing CRUD methods for Resume
    @PostMapping("/create")
    @Operation(summary = "创建简历")
    public Result createResume(@RequestBody Resume resume) {
        // Ensure createtime is set if not provided by client
        if (resume.getCreatetime() == null || resume.getCreatetime().isEmpty()) {
            resume.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
        Resume createdResume = resumeService.createResume(resume);
        return Result.success(createdResume);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取简历")
    public Result getResumeById(@PathVariable Integer id) {
        Resume resume = resumeService.getResumeById(id);
        return resume != null ? Result.success(resume) : Result.error("简历不存在");
    }

    @PutMapping("/update")
    @Operation(summary = "更新简历")
    public Result updateResume(@RequestBody Resume resume) {
        Resume updatedResume = resumeService.updateResume(resume);
        return Result.success(updatedResume);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除简历")
    public Result deleteResume(@PathVariable Integer id) {
        resumeService.deleteResume(id);
        return Result.success();
    }

    @GetMapping("/list/{username}")
    @Operation(summary = "根据用户名获取简历列表")
    public Result getResumeListByUser(@PathVariable String username) {
        List<Resume> resumeList = resumeService.getResumeListByUser(username);
        return Result.success(resumeList);
    }
}
