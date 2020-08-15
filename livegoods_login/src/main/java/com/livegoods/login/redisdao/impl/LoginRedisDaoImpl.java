package com.livegoods.login.redisdao.impl;

import com.livegoods.login.redisdao.LoginRedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public class LoginRedisDaoImpl implements LoginRedisDao {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void set(long key, int value) {

        redisTemplate.opsForValue().set(String.valueOf(key),value, Duration.ofMinutes(2L));

    }

    @Override
    public Integer get(long key) {

        return (Integer) redisTemplate.opsForValue().get((String.valueOf(key)));
    }

    @Override
    public void delete(long key) {
        redisTemplate.delete(String.valueOf(key));
    }
}
