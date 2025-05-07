package org.SETrain.CareerNavigator.Mapper;

import org.SETrain.CareerNavigator.Entity.Resume;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ResumeMapper {
  // 获取用户最新的简历ID
  @Select("SELECT id FROM resume WHERE username = #{username} ORDER BY createtime DESC LIMIT 1")
  Integer getLatestResumeIdByUsername(String username);

  // 根据用户名获取最新的简历信息
  @Select("SELECT * FROM resume WHERE username = #{username} ORDER BY createtime DESC LIMIT 1")
  Resume getResumeByUsername(String username);

  // 根据简历ID获取简历信息
  @Select("SELECT * FROM resume WHERE id = #{id}")
  Resume getResumeById(Integer id);
}
