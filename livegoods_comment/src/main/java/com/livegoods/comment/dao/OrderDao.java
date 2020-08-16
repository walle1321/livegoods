package com.livegoods.comment.dao;

import com.jyj.livegoods.pojo.Order;

public interface OrderDao {
    //通过OrderId查询订单信息
    Order finfByOrderId(String orderId);

    //修改订单的commentState
    void updateCommentState(String orderId, int commentState);
}
