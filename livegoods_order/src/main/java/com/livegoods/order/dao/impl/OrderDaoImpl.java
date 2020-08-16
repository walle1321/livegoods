package com.livegoods.order.dao.impl;

import com.jyj.livegoods.pojo.Order;
import com.livegoods.order.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Order> findByUsername(String username) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("username").is(username)
        );
        return mongoTemplate.find(query,Order.class);
    }
}
