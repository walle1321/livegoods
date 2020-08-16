package com.livegoods.buyaction.message.consumer.redisdao;

import com.jyj.livegoods.pojo.Item;

public interface ConsumerRedisDao {

    //redis中添加或修改
    boolean set(String key,Object value);

    Item get(String key);
}
