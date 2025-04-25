package org.SETrain.CareerNavigator.provider;

import org.SETrain.CareerNavigator.Entity.User;

public class UserSqlProvider {
    public String buildUpdateSql(User user) {
        StringBuilder sql = new StringBuilder();
        System.out.println("User数据" + user);
        sql.append("UPDATE user SET ");

        if (user.getUsername() != null && !user.getUsername().isEmpty()) {
            sql.append("username = #{username}, ");
        }
        if (user.getNickname() != null && !user.getNickname().isEmpty()) {
            sql.append("nickname = #{nickname}, ");
        }
        if (user.getSex() != null && !user.getSex().isEmpty()) {
            sql.append("sex = #{sex}, ");
        }
        if (user.getAge() != null) {
            sql.append("age = #{age}, ");
        }
        if (user.getEducation() != null && !user.getEducation().isEmpty()) {
            sql.append("education = #{education}, ");
        }
        if (user.getMajor() != null && !user.getMajor().isEmpty()) {
            sql.append("major = #{major}, ");
        }
        if (user.getTel() != null && !user.getTel().isEmpty()) {
            sql.append("tel = #{tel}, ");
        }
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            sql.append("email = #{email}, ");
        }
        if (user.getExpectedjob() != null && !user.getExpectedjob().isEmpty()) {
            sql.append("expectedjob = #{expectedjob}, ");
        }
        if (user.getGraduationtime() != null && !user.getGraduationtime().isEmpty()) {
            sql.append("graduationtime = #{graduationtime}, ");
        }
        if (user.getRegistertime() != null && !user.getRegistertime().isEmpty()) {
            sql.append("registertime = #{registertime}, ");
        }

        // 删除最后一个多余的逗号
        int lastComma = sql.lastIndexOf(",");
        if (lastComma != -1) {
            sql.deleteCharAt(lastComma);
        }

        sql.append(" WHERE id = #{id}");

        return sql.toString();
    }
}
