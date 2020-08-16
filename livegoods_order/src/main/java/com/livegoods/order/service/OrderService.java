package com.livegoods.order.service;

import com.jyj.livegoods.pojo.Order;

import java.util.List;

public interface OrderService {
    //展示用户订单
    List<Order> showOrder(String user);
}
