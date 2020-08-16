package com.livegoods.buyaction.redisdao.impl;

import com.jyj.livegoods.pojo.Item;
import com.livegoods.buyaction.redisdao.BuyactionRedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BuyactionRedisDaoImpl implements BuyactionRedisDao {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public Item get(String key) {
        return (Item) redisTemplate.opsForValue().get(key);
    }
}
