package com.livegoods.order.dao;

import com.jyj.livegoods.pojo.Order;

import java.util.List;

public interface OrderDao {

    //通过用户名查询订单
    List<Order> findByUsername(String username);
}
