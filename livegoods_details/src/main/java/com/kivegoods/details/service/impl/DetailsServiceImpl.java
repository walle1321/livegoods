package com.kivegoods.details.service.impl;

import com.jyj.livegoods.pojo.Item;
import com.kivegoods.details.dao.DetailsDao;
import com.kivegoods.details.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DetailsServiceImpl implements DetailsService {


    @Autowired
    private DetailsDao detailsDao;

    @Override
    @Cacheable(cacheNames = "com:livegoods:details",key = "'getDetails('+#id+')'")
    public Item showDetails(String id) {
        Item item = detailsDao.selectById(id);
        System.out.println("要存入缓存的数据"+item.toString());
        return item;
    }
}
