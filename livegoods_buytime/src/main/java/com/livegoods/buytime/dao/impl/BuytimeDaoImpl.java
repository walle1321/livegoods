package com.livegoods.buytime.dao.impl;

import com.jyj.livegoods.pojo.Item;
import com.livegoods.buytime.dao.BuytimeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class BuytimeDaoImpl implements BuytimeDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Date findById(String id) {
        Item item = mongoTemplate.findById(id, Item.class);
        return item.getBuytime();
    }
}
