package org.SETrain.CareerNavigator.Mapper;

import org.SETrain.CareerNavigator.Entity.Discussion;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DiscussionMapper {

    /**
     * 添加新评论/主题帖
     */
    @Insert("INSERT INTO discussion (user_id, content, parent_id, create_time, title, jobId) " +
            "VALUES (#{userId}, #{content}, #{parentId}, #{createTime}, #{title}, #{jobId})")
    int insert(Discussion discussion);
    /**
     * 根据父ID查询子评论（含主题帖）
     */
    @Select("SELECT * FROM discussion WHERE parent_id = #{parentId} ORDER BY create_time DESC")
    List<Discussion> findByParentId(Integer parentId);

    /**
     * 删除评论（级联删除子评论需额外处理，此处简化为单条删除）
     */
    @Delete("DELETE FROM discussion WHERE id = #{id}")
    int deleteById(Integer id);

    @Select("SELECT * FROM discussion WHERE jobId = #{jobId} ORDER BY create_time DESC")
    List<Discussion> findByJobId(Integer jobId);
}
