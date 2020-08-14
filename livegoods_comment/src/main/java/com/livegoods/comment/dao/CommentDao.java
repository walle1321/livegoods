package com.livegoods.comment.dao;

import com.jyj.livegoods.pojo.Comment;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface CommentDao {

    //通过商品id分页搜索商品评论
    List<Comment> selectByItemId(Query query);

    //分页查询要搜索页码，进行判断是否是最后一页
    long selectCountByItemId(String itemId);
}
