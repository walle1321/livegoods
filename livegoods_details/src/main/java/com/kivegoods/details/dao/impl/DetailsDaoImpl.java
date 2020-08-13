package com.kivegoods.details.dao.impl;

import com.jyj.livegoods.pojo.Item;
import com.kivegoods.details.dao.DetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DetailsDaoImpl implements DetailsDao {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Value("${livegoods.banner.nginx.host}")
    private String nginxHost;

    @Override
    public Item selectById(String id) {
        Item item = mongoTemplate.findById(id, Item.class);
        List<String> imgs = item.getImgs();
        for (int i = 0; i < imgs.size(); i++) {
            imgs.set(i,nginxHost+imgs.get(i));
        }
        item.setImgs(imgs);
        return item;
    }
}
