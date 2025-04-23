package org.SETrain.CareerNavigator.Mapper;

import org.SETrain.CareerNavigator.Entity.User;
import org.SETrain.CareerNavigator.provider.UserSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
  @Select("SELECT * FROM user WHERE id = #{id}")
  User findById(Integer id);

  @Select("SELECT * FROM user WHERE username = #{username}")
  User findByUsername(String username);

  @Select("SELECT * FROM user")
  List<User> findAll();

  @Insert("INSERT INTO user (username, nickname, sex, age, education, major, tel, email, expectedjob, graduationtime, registertime) "
      +
      "VALUES (#{username}, #{nickname}, #{sex}, #{age}, #{education}, #{major}, #{tel}, #{email}, #{expectedjob}, #{graduationtime}, #{registertime})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  int insert(User user);

    @Update("UPDATE user SET username=#{newUsername} WHERE username=#{oldUsername}")
  int updateUsername(@Param("oldUsername") String oldUsername, @Param("newUsername") String newUsername);

  @Update("UPDATE user SET nickname=#{nickname} WHERE id=#{id}")
  int updateNickname(@Param("id") Integer id, @Param("nickname") String nickname);

  @UpdateProvider(type = UserSqlProvider.class, method = "buildUpdateSql")
  int update(User user);

  @Update("UPDATE user SET sex=#{sex} WHERE id=#{id}")
  int updateSex(@Param("id") Integer id, @Param("sex") String sex);

  @Update("UPDATE user SET age=#{age} WHERE id=#{id}")
  int updateAge(@Param("id") Integer id, @Param("age") Integer age);

  @Update("UPDATE user SET education=#{education} WHERE id=#{id}")
  int updateEducation(@Param("id") Integer id, @Param("education") String education);

  @Update("UPDATE user SET major=#{major} WHERE id=#{id}")
  int updateMajor(@Param("id") Integer id, @Param("major") String major);

  @Update("UPDATE user SET tel=#{tel} WHERE id=#{id}")
  int updateTel(@Param("id") Integer id, @Param("tel") String tel);

  @Update("UPDATE user SET email=#{email} WHERE id=#{id}")
  int updateEmail(@Param("id") Integer id, @Param("email") String email);

  @Update("UPDATE user SET expectedjob=#{expectedjob} WHERE id=#{id}")
  int updateExpectedJob(@Param("id") Integer id, @Param("expectedjob") String expectedjob);

  @Update("UPDATE user SET graduationtime=#{graduationtime} WHERE id=#{id}")
  int updateGraduationTime(@Param("id") Integer id, @Param("graduationtime") String graduationtime);


  @Delete("DELETE FROM user WHERE id = #{id}")
  int deleteById(Integer id);
}