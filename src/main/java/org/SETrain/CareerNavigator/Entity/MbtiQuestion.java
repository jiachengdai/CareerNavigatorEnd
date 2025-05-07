package org.SETrain.CareerNavigator.Entity;

import java.util.Objects;

public class MbtiQuestion {
  private Integer id;
  private String questionText;
  private String dimension;
  private Integer sort;
  private Integer status;

  @Override
  public String toString() {
    return "MbtiQuestion{" +
        "id=" + id +
        ", questionText='" + questionText + '\'' +
        ", dimension='" + dimension + '\'' +
        ", sort=" + sort +
        ", status=" + status +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    MbtiQuestion that = (MbtiQuestion) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(questionText, that.questionText) &&
        Objects.equals(dimension, that.dimension) &&
        Objects.equals(sort, that.sort) &&
        Objects.equals(status, that.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, questionText, dimension, sort, status);
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getQuestionText() {
    return questionText;
  }

  public void setQuestionText(String questionText) {
    this.questionText = questionText;
  }

  public String getDimension() {
    return dimension;
  }

  public void setDimension(String dimension) {
    this.dimension = dimension;
  }

  public Integer getSort() {
    return sort;
  }

  public void setSort(Integer sort) {
    this.sort = sort;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}