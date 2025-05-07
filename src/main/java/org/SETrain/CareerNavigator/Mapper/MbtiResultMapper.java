package org.SETrain.CareerNavigator.Mapper;

import org.SETrain.CareerNavigator.Entity.MbtiResult;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MbtiResultMapper {

  @Select("SELECT * FROM mbti_result WHERE username = #{username} ORDER BY test_time DESC")
  List<MbtiResult> findByUsername(@Param("username") String username);

  @Select("SELECT * FROM mbti_result WHERE id = #{id}")
  MbtiResult findById(@Param("id") Integer id);

  @Insert("INSERT INTO mbti_result (username, mbti_type, test_time, e_score, i_score, s_score, n_score, t_score, f_score, j_score, p_score) "
      +
      "VALUES (#{username}, #{mbtiType}, NOW(), #{eScore}, #{iScore}, #{sScore}, #{nScore}, #{tScore}, #{fScore}, #{jScore}, #{pScore})")
  void insertResult(
      @Param("username") String username,
      @Param("mbtiType") String mbtiType,
      @Param("eScore") Integer eScore,
      @Param("iScore") Integer iScore,
      @Param("sScore") Integer sScore,
      @Param("nScore") Integer nScore,
      @Param("tScore") Integer tScore,
      @Param("fScore") Integer fScore,
      @Param("jScore") Integer jScore,
      @Param("pScore") Integer pScore);

  // 获取用户最新测试结果
  @Select("SELECT * FROM mbti_result WHERE username = #{username} ORDER BY test_time DESC LIMIT 1")
  MbtiResult getLatestResultByUsername(@Param("username") String username);

  @Delete("DELETE FROM mbti_result WHERE id = #{id}")
  int deleteById(@Param("id") Integer id);
}