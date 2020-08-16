package com.livegoods.buyaction.message.consumer.dao;

public interface ConsumerDao {
    //通过id修改是否已经租出去了
    long update(String id,boolean lease);
}
