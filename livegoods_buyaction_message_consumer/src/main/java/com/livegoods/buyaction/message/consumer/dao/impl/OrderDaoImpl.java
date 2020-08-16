package com.livegoods.buyaction.message.consumer.dao.impl;

import com.jyj.livegoods.pojo.Order;
import com.livegoods.buyaction.message.consumer.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(Order order) {
        mongoTemplate.save(order);

    }
}
