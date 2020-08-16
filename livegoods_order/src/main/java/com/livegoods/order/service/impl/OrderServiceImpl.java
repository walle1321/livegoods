package com.livegoods.order.service.impl;

import com.jyj.livegoods.pojo.Order;
import com.livegoods.order.dao.OrderDao;
import com.livegoods.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> showOrder(String user) {
        return orderDao.findByUsername(user);
    }
}
