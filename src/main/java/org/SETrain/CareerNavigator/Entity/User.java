package org.SETrain.CareerNavigator.Entity;

import java.util.Objects;

public class User {
    private Integer id;
    private String username;
    private String nickname;
    private String sex;
    private Integer age;
    private String education;
    private String major;
    private String tel;
    private String email;
    private String expectedjob;
    private String graduationtime;
    private String registertime;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", education='" + education + '\'' +
                ", major='" + major + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", expectedjob='" + expectedjob + '\'' +
                ", graduationtime='" + graduationtime + '\'' +
                ", registertime='" + registertime + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpectedjob() {
        return expectedjob;
    }

    public void setExpectedjob(String expectedjob) {
        this.expectedjob = expectedjob;
    }

    public String getGraduationtime() {
        return graduationtime;
    }

    public void setGraduationtime(String graduationtime) {
        this.graduationtime = graduationtime;
    }

    public String getRegistertime() {
        return registertime;
    }

    public void setRegistertime(String registertime) {
        this.registertime = registertime;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username)
                && Objects.equals(nickname, user.nickname) && Objects.equals(sex, user.sex)
                && Objects.equals(age, user.age) && Objects.equals(education, user.education)
                && Objects.equals(major, user.major) && Objects.equals(tel, user.tel)
                && Objects.equals(email, user.email) && Objects.equals(expectedjob, user.expectedjob)
                && Objects.equals(graduationtime, user.graduationtime)
                && Objects.equals(registertime, user.registertime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, nickname, sex, age, education, major, tel, email, expectedjob, graduationtime,
                registertime);
    }
}
