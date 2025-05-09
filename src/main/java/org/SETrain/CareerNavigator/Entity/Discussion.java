package org.SETrain.CareerNavigator.Entity;

import java.util.Date;

public class Discussion {
    private Integer id;
    private String userId;      // 用户ID（关联用户表）
    private String content;     // 评论内容
    private Integer parentId;   // 父评论ID（0表示顶级评论）
    private Date createTime;    // 创建时间
    private String title;       // 评论标题（可选，用于主题帖）
    private Integer jobId;     // Add this field

    // Getter和Setter方法
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Integer getParentId() { return parentId; }
    public void setParentId(Integer parentId) { this.parentId = parentId; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    // Add getter/setter
    public Integer getJobId() { return jobId; }
    public void setJobId(Integer jobId) { this.jobId = jobId; }
}