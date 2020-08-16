package com.livegoods.buyaction.message.consumer.service.impl;

import com.jyj.livegoods.pojo.Item;
import com.jyj.livegoods.pojo.Order;
import com.livegoods.buyaction.message.consumer.dao.ConsumerDao;
import com.livegoods.buyaction.message.consumer.dao.OrderDao;
import com.livegoods.buyaction.message.consumer.redisdao.ConsumerRedisDao;
import com.livegoods.buyaction.message.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerDao consumerDao;
    @Autowired
    private ConsumerRedisDao redisDao;
    @Autowired
    private OrderDao orderDao;
    @Value("${livegoods.redis.item.prefix}")
    private String prefix;
    @Value("${livegoods.redis.item.suffix}")
    private String suffix;

    /**
     * 购买流程：
     * 1、 更新缓存中的商品数据。并判断更新结果。 更新成功执行后续。 原子操作。
     * 2、 更新MongoDB中的商品数据。
     * 3、 记录订单信息
     * 4、 返回处理结果
     * @param id 商品主键
     * @param user 用户手机号
     * @return
     */
    @Override
    public boolean buyaction(String id, String user) {
        //修改redis
        String key = prefix + "::" + suffix + "(" + id + ")";
        //获取redis
        Item item = redisDao.get(key);
        item.setLease(true);
        //修改,其数据处理时会有原子操作，是否redis中已经修改了，如果修改了就不执行
        boolean isSet = redisDao.set(key,item);
        if (isSet){
            //修改mongodb
            long update = consumerDao.update(id, true);
            if (update==1){
                //创建订单
                Order order = new Order();
                order.setCommentState(0);
                order.setHouseType(item.getHouseType4Search());
                order.setImg(item.getImg());
                order.setItemId(item.getId());
                order.setPrice(item.getPrice().toString());
                order.setRentType(item.getRentType());
                order.setTitle(item.getTitle());
                order.setUsername(user);
                orderDao.save(order);
                return true;
            }
        }
        return false;
    }
}
