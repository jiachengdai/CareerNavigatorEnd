package org.SETrain.CareerNavigator.DTO;

public class InterviewMsgRecord {
    String msg;
    Integer interviewid;
    String role;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getInterviewid() {
        return interviewid;
    }

    public void setInterviewid(Integer interviewid) {
        this.interviewid = interviewid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "InterviewMsgRecord{" +
                "msg='" + msg + '\'' +
                ", interviewid=" + interviewid +
                ", role='" + role + '\'' +
                '}';
    }
}
