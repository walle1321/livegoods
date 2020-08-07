package com.livegoods.banner.dao.impl;

import com.jyj.livegoods.pojo.Banner;
import com.livegoods.banner.dao.BannerDao;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BannerDaoImpl implements BannerDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Banner> selectBanner(Query query) {
        List<Banner> list = mongoTemplate.find(query, Banner.class);
        return list;
    }
}
