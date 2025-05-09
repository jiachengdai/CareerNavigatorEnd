package org.SETrain.CareerNavigator.Mapper;

import org.SETrain.CareerNavigator.Entity.Resume;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
  // 更新简历评估结果（分数、分析、建议）
  @Update("UPDATE resume SET score = #{score}, analysis = #{analysis}, advice = #{advice} WHERE id = #{id}")
  void updateAssessmentResult(Integer id, Integer score, String analysis, String advice);

  @Insert("INSERT INTO resume (username, title, createtime, score, analysis, advice, type, content) " +
          "VALUES (#{username}, #{title}, #{createtime}, #{score}, #{analysis}, #{advice}, #{type}, #{content})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insert(Resume resume);

  // 根据ID查询简历
  @Select("SELECT * FROM resume WHERE id = #{id}")
  Resume selectById(Integer id);

  // 更新简历（需与实体类字段匹配）
  @Update("UPDATE resume SET " +
          "username = #{username}, " +
          "title = #{title}, " +
          "createtime = #{createtime}, " +
          "score = #{score}, " +
          "analysis = #{analysis}, " +
          "advice = #{advice}, " +
          "type = #{type}, " +
          "content = #{content} " +
          "WHERE id = #{id}")
  void update(Resume resume);

  @Delete("DELETE FROM resume WHERE id = #{id}")
  void deleteById(Integer id);

  // 新增：根据用户名查询简历列表（按创建时间倒序）
  @Select("SELECT * FROM resume WHERE username = #{username} ORDER BY createtime DESC")
  List<Resume> selectByUsername(String username);


}
