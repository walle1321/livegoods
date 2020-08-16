package com.livegoods.buyaction.message.consumer.config;

import com.livegoods.redis.config.RedisCacheConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class MessageConsumerConfiguration extends RedisCacheConfiguration {

    @Override
    @Bean
    protected RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return super.redisTemplate(redisConnectionFactory);
    }


}
