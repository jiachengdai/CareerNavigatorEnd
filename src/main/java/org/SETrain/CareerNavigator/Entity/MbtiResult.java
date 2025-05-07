package org.SETrain.CareerNavigator.Entity;

import java.util.Date;
import java.util.Objects;

public class MbtiResult {
  private Integer id;
  private String username;
  private String mbtiType;
  private Integer eScore;
  private Integer iScore;
  private Integer sScore;
  private Integer nScore;
  private Integer tScore;
  private Integer fScore;
  private Integer jScore;
  private Integer pScore;
  private Date testTime;

  @Override
  public String toString() {
    return "MbtiResult{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", mbtiType='" + mbtiType + '\'' +
        ", eScore=" + eScore +
        ", iScore=" + iScore +
        ", sScore=" + sScore +
        ", nScore=" + nScore +
        ", tScore=" + tScore +
        ", fScore=" + fScore +
        ", jScore=" + jScore +
        ", pScore=" + pScore +
        ", testTime=" + testTime +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    MbtiResult that = (MbtiResult) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(username, that.username) &&
        Objects.equals(mbtiType, that.mbtiType) &&
        Objects.equals(eScore, that.eScore) &&
        Objects.equals(iScore, that.iScore) &&
        Objects.equals(sScore, that.sScore) &&
        Objects.equals(nScore, that.nScore) &&
        Objects.equals(tScore, that.tScore) &&
        Objects.equals(fScore, that.fScore) &&
        Objects.equals(jScore, that.jScore) &&
        Objects.equals(pScore, that.pScore) &&
        Objects.equals(testTime, that.testTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, mbtiType, eScore, iScore, sScore, nScore, tScore, fScore, jScore, pScore,
        testTime);
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

  public String getMbtiType() {
    return mbtiType;
  }

  public void setMbtiType(String mbtiType) {
    this.mbtiType = mbtiType;
  }

  public Integer geteScore() {
    return eScore;
  }

  public void seteScore(Integer eScore) {
    this.eScore = eScore;
  }

  public Integer getiScore() {
    return iScore;
  }

  public void setiScore(Integer iScore) {
    this.iScore = iScore;
  }

  public Integer getsScore() {
    return sScore;
  }

  public void setsScore(Integer sScore) {
    this.sScore = sScore;
  }

  public Integer getnScore() {
    return nScore;
  }

  public void setnScore(Integer nScore) {
    this.nScore = nScore;
  }

  public Integer gettScore() {
    return tScore;
  }

  public void settScore(Integer tScore) {
    this.tScore = tScore;
  }

  public Integer getfScore() {
    return fScore;
  }

  public void setfScore(Integer fScore) {
    this.fScore = fScore;
  }

  public Integer getjScore() {
    return jScore;
  }

  public void setjScore(Integer jScore) {
    this.jScore = jScore;
  }

  public Integer getpScore() {
    return pScore;
  }

  public void setpScore(Integer pScore) {
    this.pScore = pScore;
  }

  public Date getTestTime() {
    return testTime;
  }

  public void setTestTime(Date testTime) {
    this.testTime = testTime;
  }
}