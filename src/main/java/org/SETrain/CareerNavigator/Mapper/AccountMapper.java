package org.SETrain.CareerNavigator.Mapper;

import org.SETrain.CareerNavigator.Entity.Account;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AccountMapper {
    // 根据用户名查找用户
    @Select("SELECT * FROM account WHERE username = #{username}")
    Account findByUsername(String username);

    // 注册新用户
    @Insert("INSERT INTO account(username, password, type) VALUES(#{username}, #{password}, #{type})")
//    @Options(useGeneratedKeys = true, keyProperty = "username")
int register(@Param("username") String username, @Param("password") String password,  @Param("type") Integer type);


    // 更新密码
    @Update("UPDATE account SET password = #{password} WHERE username = #{username}")
    int updatePassword(String username, String password);

    // 更新用户名
    @Update("UPDATE account SET username = #{newUsername} WHERE username = #{oldUsername}")
    int updateUsername(@Param("oldUsername") String oldUsername, @Param("newUsername") String newUsername);

    // 检查用户名是否存在
    @Select("SELECT COUNT(*) FROM account WHERE username = #{username}")
    int checkUsername(String username);
}
