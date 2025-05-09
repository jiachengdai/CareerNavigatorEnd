package org.SETrain.CareerNavigator.Service.Impl;

import org.SETrain.CareerNavigator.Entity.Discussion;
import org.SETrain.CareerNavigator.Mapper.DiscussionMapper;
import org.SETrain.CareerNavigator.Service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DiscussionServiceImpl implements DiscussionService {

    @Autowired
    private DiscussionMapper discussionMapper;

    @Override
    public String publish(Discussion discussion) {
        discussion.setCreateTime(new Date());
        int result = discussionMapper.insert(discussion);
        return result > 0 ? "发布成功" : "发布失败";
    }

    @Override
    public String publish(Discussion discussion, Integer jobId) {
        discussion.setJobId(jobId); // 新增岗位关联
        discussion.setCreateTime(new Date());
        int result = discussionMapper.insert(discussion);
        return result > 0 ? "发布成功" : "发布失败";
    }

    @Override
    public List<Discussion> getDiscussionsByJobId(Integer jobId) {
        return discussionMapper.findByJobId(jobId); // 新增按岗位查询
    }
    @Override
    public List<Discussion> getDiscussions(Integer parentId) {
        return discussionMapper.findByParentId(parentId);
    }

    @Override
    public String delete(Integer id) {
        int result = discussionMapper.deleteById(id);
        return result > 0 ? "删除成功" : "删除失败（评论不存在）";
    }
}
