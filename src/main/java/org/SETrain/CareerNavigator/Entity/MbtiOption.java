package org.SETrain.CareerNavigator.Entity;

import java.util.Objects;

public class MbtiOption {
  private Integer id;
  private Integer questionId;
  private String optionText;
  private Integer score;
  private String dimensionValue;

  @Override
  public String toString() {
    return "MbtiOption{" +
        "id=" + id +
        ", questionId=" + questionId +
        ", optionText='" + optionText + '\'' +
        ", score=" + score +
        ", dimensionValue='" + dimensionValue + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    MbtiOption that = (MbtiOption) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(questionId, that.questionId) &&
        Objects.equals(optionText, that.optionText) &&
        Objects.equals(score, that.score) &&
        Objects.equals(dimensionValue, that.dimensionValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, questionId, optionText, score, dimensionValue);
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Integer questionId) {
    this.questionId = questionId;
  }

  public String getOptionText() {
    return optionText;
  }

  public void setOptionText(String optionText) {
    this.optionText = optionText;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public String getDimensionValue() {
    return dimensionValue;
  }

  public void setDimensionValue(String dimensionValue) {
    this.dimensionValue = dimensionValue;
  }
}