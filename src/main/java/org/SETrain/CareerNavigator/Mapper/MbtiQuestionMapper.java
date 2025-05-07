package org.SETrain.CareerNavigator.Mapper;

import org.SETrain.CareerNavigator.Entity.MbtiQuestion;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MbtiQuestionMapper {

  // 获取所有有效的题目
  @Select("SELECT * FROM mbti_question WHERE status = 1 ORDER BY sort")
  List<MbtiQuestion> findAllActiveQuestions();

  // 根据ID获取题目
  @Select("SELECT * FROM mbti_question WHERE id = #{id}")
  MbtiQuestion findById(@Param("id") Integer id);

  // 获取总题目数
  @Select("SELECT COUNT(*) FROM mbti_question WHERE status = 1")
  Integer getTotalQuestionCount();

  @Insert("INSERT INTO mbti_question(question_text, dimension, sort, status) " +
      "VALUES(#{questionText}, #{dimension}, #{sort}, #{status})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  int insert(MbtiQuestion question);

  @Update("UPDATE mbti_question SET question_text = #{questionText}, dimension = #{dimension}, " +
      "sort = #{sort}, status = #{status} WHERE id = #{id}")
  int update(MbtiQuestion question);

  @Delete("DELETE FROM mbti_question WHERE id = #{id}")
  int deleteById(@Param("id") Integer id);
}