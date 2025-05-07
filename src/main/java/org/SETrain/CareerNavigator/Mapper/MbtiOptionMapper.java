package org.SETrain.CareerNavigator.Mapper;

import org.SETrain.CareerNavigator.Entity.MbtiOption;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MbtiOptionMapper {

    @Select("SELECT * FROM mbti_option WHERE question_id = #{questionId}")
    List<String> findByQuestionId(@Param("questionId") Integer questionId);

    @Select("SELECT * FROM mbti_option WHERE id = #{id}")
    MbtiOption findById(@Param("id") Integer id);

    // 根据题目ID和选项文本获取维度值
    @Select("SELECT dimension_value FROM mbti_option WHERE question_id = #{questionId} AND option_text = #{optionText}")
    String getDimensionValueByQuestionAndText(
            @Param("questionId") Integer questionId,
            @Param("optionText") String optionText);

    // 根据题目ID和选项文本获取分数
    @Select("SELECT score FROM mbti_option WHERE question_id = #{questionId} AND option_text = #{optionText}")
    Integer getScoreByQuestionAndText(
            @Param("questionId") Integer questionId,
            @Param("optionText") String optionText);

    @Insert("INSERT INTO mbti_option(question_id, option_text, score, dimension_value) " +
            "VALUES(#{questionId}, #{optionText}, #{score}, #{dimensionValue})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(MbtiOption option);

    @Update("UPDATE mbti_option SET question_id = #{questionId}, option_text = #{optionText}, " +
            "score = #{score}, dimension_value = #{dimensionValue} WHERE id = #{id}")
    int update(MbtiOption option);

    @Delete("DELETE FROM mbti_option WHERE id = #{id}")
    int deleteById(@Param("id") Integer id);

    @Delete("DELETE FROM mbti_option WHERE question_id = #{questionId}")
    int deleteByQuestionId(@Param("questionId") Integer questionId);
}