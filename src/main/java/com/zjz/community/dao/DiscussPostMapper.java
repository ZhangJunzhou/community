package com.zjz.community.dao;

import com.zjz.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    // 根据用户ID查找该用户的帖子,当userID为0，返回的是所有的帖子；为具体的userId时，具体人的帖子
    // offset页号，limit行一页的数量
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    // Param起别名，
    // 如果方法只有一个参数，并且在<if>里使用，就必须加别名；
    int selectDiscussPostRows(@Param("userId") int userId);
}
