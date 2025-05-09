package org.SETrain.CareerNavigator.Controller;

import org.SETrain.CareerNavigator.Entity.Discussion;
import org.SETrain.CareerNavigator.Entity.Result;
import org.SETrain.CareerNavigator.Service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discussions")
public class DiscussionController {

    @Autowired
    private DiscussionService discussionService;

    /**
     * 发布评论/主题帖
     */
    @PostMapping("/publish")
    public Result publish(@RequestBody Discussion discussion) {
        String msg = discussionService.publish(discussion);
        return msg.contains("成功") ? Result.success(msg) : Result.error(msg);
    }

    /**
     * 获取评论列表（parentId=0时为主题帖列表）
     */
    @GetMapping("/list/{parentId}")
    public Result getList(@PathVariable Integer parentId) {
        return Result.success(discussionService.getDiscussions(parentId));
    }

    /**
     * 删除评论
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        String msg = discussionService.delete(id);
        return msg.contains("成功") ? Result.success(msg) : Result.error(msg);
    }

    @GetMapping("/job/{jobId}")
    public Result getByJobId(@PathVariable Integer jobId) {
        List<Discussion> discussions = discussionService.getDiscussionsByJobId(jobId);
        return Result.success(discussions);
    }

    @PostMapping("/publish/{jobId}")
    public Result publish(@RequestBody Discussion discussion, @PathVariable Integer jobId) {
        String result = discussionService.publish(discussion, jobId);
        return result.equals("发布成功") ? Result.success() : Result.error(result);
    }
}
