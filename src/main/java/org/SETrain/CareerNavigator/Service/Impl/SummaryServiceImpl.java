 package org.SETrain.CareerNavigator.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.SETrain.CareerNavigator.Entity.Summary;
import org.SETrain.CareerNavigator.Mapper.SummaryMapper;
import org.SETrain.CareerNavigator.Service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {
    @Autowired
    private SummaryMapper summaryMapper;
    @Override
    public Summary getSummaryByResumeId(Integer resumeId) {
        // TODO: 实现从数据库获取个人总结的逻辑
        return summaryMapper.getSummaryByResumeId(resumeId);
    }
}
