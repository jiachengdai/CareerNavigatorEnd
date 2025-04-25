package org.SETrain.CareerNavigator.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ResumeMapper {
  // 获取用户最新的简历ID
  @Select("SELECT id FROM resume WHERE username = #{username} ORDER BY createtime DESC LIMIT 1")
  Integer getLatestResumeIdByUsername(String username);
}
