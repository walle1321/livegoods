package com.livegoods.comment.dao.impl;

import com.jyj.livegoods.pojo.Comment;
import com.livegoods.comment.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Comment> selectByItemId(Query query) {
        return mongoTemplate.find(query,Comment.class);
    }

    @Override
    public long selectCountByItemId(String itemId) {

        //查询出所有的数据
        Query query = new Query();
        query.addCriteria(
                new Criteria().andOperator(
                        Criteria.where("itemId").is(itemId)
                )
        );
        List<Comment> comments = mongoTemplate.find(query, Comment.class);
        System.out.println(comments.size());
        return comments.size();

    }
}
