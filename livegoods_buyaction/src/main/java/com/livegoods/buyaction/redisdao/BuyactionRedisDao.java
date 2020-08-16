package com.livegoods.buyaction.redisdao;

import com.jyj.livegoods.pojo.Item;

public interface BuyactionRedisDao {

    //查询商品
    Item get(String key);
}
