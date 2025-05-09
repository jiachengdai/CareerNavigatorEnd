package org.SETrain.CareerNavigator.Service.Impl;

import org.SETrain.CareerNavigator.Entity.Job;
import org.SETrain.CareerNavigator.Mapper.JobMapper;
import org.SETrain.CareerNavigator.Service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class JobServiceImpl implements JobService {
    private static final Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);
    @Autowired
    private JobMapper jobMapper;
    @Autowired  // Add this line to inject DeepSeekApiUtil
    private ChatClient chatClient;

    @Override
    public List<Job> getAllJobs() {
        return jobMapper.findAllJobs();
    }

    @Override
    public Job getJobById(Integer id) {
        return jobMapper.findJobById(id);
    }

    @Override
    public String analyzeJob(Integer jobId) {
        Job job = jobMapper.findJobById(jobId);
        if (job == null) {
            logger.warn("职位解读失败，未找到岗位信息，jobId={}", jobId);
            return "未找到该岗位信息";
        }
        try {
            String question = "请分析该职位的职责、要求和发展前景：" + job.getJobdescription();
            logger.info("开始调用AI分析职位，jobId={}, 提问内容：{}", jobId, question);
            String aiResult = chatClient.call(question);
            logger.info("AI分析职位成功，jobId={}, 结果：{}", jobId, aiResult);
            return aiResult;
        } catch (RestClientException e) {
            logger.error("AI接口网络异常，jobId={}, 异常信息：{}", jobId, e.getMessage(), e);
            return "AI接口网络异常：" + e.getMessage();
        } catch (Exception e) {
            logger.error("职位解读失败，jobId={}, 异常信息：{}", jobId, e.getMessage(), e);
            return "职位解读失败：" + e.getMessage();
        }
    }




    }

