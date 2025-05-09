package org.SETrain.CareerNavigator.Entity;

import java.util.Objects;

public class Resume {
    private Integer id;
    private String username;
    private String  title;
    private  String createtime;
    private Integer score;
    private String  analysis;
    private String advice;
    private String type;
    private String content;  // Add this field

    @Override
    public String toString() {
        return "Resume{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", createtime='" + createtime + '\'' +
                ", score=" + score +
                ", analysis='" + analysis + '\'' +
                ", advice='" + advice + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(id, resume.id) && Objects.equals(username, resume.username) && Objects.equals(title, resume.title) && Objects.equals(createtime, resume.createtime) && Objects.equals(score, resume.score) && Objects.equals(analysis, resume.analysis) && Objects.equals(advice, resume.advice) && Objects.equals(type, resume.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, title, createtime, score, analysis, advice, type);
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {  // Add this getter
        return content;
    }

    public void setContent(String content) {  // Add this setter
        this.content = content;
    }
}
