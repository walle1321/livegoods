package com.livegoods.buyaction.message.consumer.dao.impl;

import com.jyj.livegoods.pojo.Item;
import com.livegoods.buyaction.message.consumer.dao.ConsumerDao;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class ConsumerDaoImpl implements ConsumerDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public long update(String id, boolean lease) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("id").is(id)
        );
        Update update = Update.update("isLease", lease);
        UpdateResult result = mongoTemplate.updateFirst(query, update, Item.class);
        return result.getModifiedCount();
    }
}
