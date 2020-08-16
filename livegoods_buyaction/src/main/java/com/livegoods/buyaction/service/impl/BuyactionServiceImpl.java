package com.livegoods.buyaction.service.impl;

import com.jyj.livegoods.pojo.Item;
import com.livegoods.buyaction.redisdao.BuyactionRedisDao;
import com.livegoods.buyaction.service.BuyactionService;
import com.livegoods.commons.message.LivegoodsBuyMessage;
import com.livegoods.commons.vo.LivegoodsResult;
import com.livegoods.message.publisher.LivegoodsMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BuyactionServiceImpl implements BuyactionService {

    @Autowired
    private BuyactionRedisDao redisDao;
    @Autowired
    private LivegoodsMessagePublisher messagePublisher;
    @Value("${livegoods.redis.item.prefix}")
    private String prefix;
    @Value("${livegoods.redis.item.suffix}")
    private String suffix;
    @Value("${livegoods.rabbit.buy.exchange}")
    private String exchange;
    @Value("${livegoods.rabbit.buy.routingKey}")
    private String routingKey;

    /**
     * 实现流程：
     *      *  1、 访问redis，查询这个商品是否可购买
     *      *  2、 封装一个消息对象，发送消息到MQ，并等待消费者的响应
     *      *  3、 根据消息消费者响应结果，返回操作结果
     * @param id
     * @param username
     * @return
     */
    @Override
    public LivegoodsResult buy(String id, String username) {
        Item item = redisDao.get(prefix + "::" + suffix + "(" + id + ")");
        if (item.getLease()){
            return LivegoodsResult.error("已经租出去了");
        }
        LivegoodsBuyMessage message = new LivegoodsBuyMessage();
        message.setItemId(id);
        message.setUsername(username);
        Boolean result = (Boolean) messagePublisher.sendMessageSync(exchange,routingKey,message);

        if (result){
            LivegoodsResult ok = LivegoodsResult.ok();
            ok.setMsg("抢到了！");
            return ok;
        }
        return LivegoodsResult.error("没有抢到");
    }
}
