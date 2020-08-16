package com.livegoods.buyaction.message.consumer.redisdao.impl;

import com.jyj.livegoods.pojo.Item;
import com.livegoods.buyaction.message.consumer.redisdao.ConsumerRedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConsumerRedisDaoImpl implements ConsumerRedisDao {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public boolean set(final String key, final Object value) {
        try {
            redisTemplate.setEnableTransactionSupport(true);
            // 执行多次操作，且操作是原子性操作的时候，可以通过回调逻辑，实现。
            List<Object> result = redisTemplate.execute(
                    new SessionCallback<List<Object>>() {
                        public List<Object> execute(RedisOperations redisOperations) throws DataAccessException {
                            Item item = (Item) redisOperations.opsForValue().get(key);
                            if (item.getLease()) {
                                // 已出租，考虑并发环境。
                                return null;
                            }
                            // 开启事务， 通知redis后续的多条命令，是一个原子操作。
                            redisOperations.multi();
                            // 未出租， 可以更新数据
                            redisOperations.opsForValue().set(key, value);
                            // 相当于是提交事务， 通知redis，前置的命令都运行，如果有任何命令出错，回滚，没有出错提交。
                            return redisOperations.exec();
                        }
                    }
            );
            redisTemplate.setEnableTransactionSupport(false);
            if (null == result) { // 原子操作失败， 更新失败。
                return false;
            }
            return true;
        }catch(Exception e){
            // 发生任何异常，redisTemplate都会回滚事务，释放资源，但是不处理异常。
            // 如果此处没有异常处理逻辑，异常会抛到主线程（main方法）、直到虚拟机为止。
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Item get(String key) {

        return (Item) redisTemplate.opsForValue().get(key);
    }
}
