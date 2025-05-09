package org.SETrain.CareerNavigator.Service;

import org.SETrain.CareerNavigator.Entity.Discussion;

import java.util.List;

public interface DiscussionService {

    /**
     * 发布评论/主题帖
     */
    String publish(Discussion discussion);

    /**
     * 获取某主题下的评论列表（parentId=0为主题帖）
     */
    List<Discussion> getDiscussions(Integer parentId);

    /**
     * 获取某职位下的评论列表
     */
    List<Discussion> getDiscussionsByJobId(Integer jobId);
    String publish(Discussion discussion, Integer jobId);

    /**
     * 删除评论
     */
    String delete(Integer id);
}
