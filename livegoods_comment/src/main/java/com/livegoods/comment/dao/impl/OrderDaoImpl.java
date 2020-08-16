package com.livegoods.comment.dao.impl;

import com.jyj.livegoods.pojo.Order;
import com.livegoods.comment.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Order finfByOrderId(String orderId) {
        return mongoTemplate.findById(orderId,Order.class);
    }

    @Override
    public void updateCommentState(String orderId, int commentState) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(orderId));
        Update update = Update.update("commentState", commentState);
        mongoTemplate.updateFirst(query,update,Order.class);
    }
}
