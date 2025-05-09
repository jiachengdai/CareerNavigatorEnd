package org.SETrain.CareerNavigator.Service.Impl;

import org.SETrain.CareerNavigator.Entity.ResumeAssessmentRecord;
import org.SETrain.CareerNavigator.Mapper.ResumeAssessmentRecordMapper;
import org.SETrain.CareerNavigator.Service.ResumeAssessmentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResumeAssessmentRecordServiceImpl implements ResumeAssessmentRecordService {

    @Autowired
    private ResumeAssessmentRecordMapper recordMapper;

    @Override
    public void createRecord(ResumeAssessmentRecord record) {
        record.setAssessmentTime(new Date());  // 自动设置评估时间
        recordMapper.insert(record);
    }

    @Override
    public List<ResumeAssessmentRecord> getRecordsByResumeId(Integer resumeId) {
        return recordMapper.getRecordsByResumeId(resumeId);
    }

    @Override
    public void deleteRecord(Integer id) {
        recordMapper.deleteById(id);
    }
}
