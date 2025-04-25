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

  @UpdateProvider(type = UserSqlProvider.class, method = "buildUpdateSql")
  int update(User user);

  @Delete("DELETE FROM user WHERE id = #{id}")
  int deleteById(Integer id);
}