package com.livegoods.recommedation.dao.impl;

import com.jyj.livegoods.pojo.Item;
import com.livegoods.recommedation.dao.RecommedationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecommedationDaoImpl implements RecommedationDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Item> select(Query query) {
        return mongoTemplate.find(query,Item.class);
    }
}
