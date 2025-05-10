package org.SETrain.CareerNavigator.Mapper;

import org.SETrain.CareerNavigator.Entity.Summary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SummaryMapper {
    @Select("SELECT * FROM summary WHERE resumeid = #{resumeId}")
    Summary getSummaryByResumeId(Integer resumeId);
}
