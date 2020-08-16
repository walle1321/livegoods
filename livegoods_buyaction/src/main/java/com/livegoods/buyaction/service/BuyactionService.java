package com.livegoods.buyaction.service;

import com.livegoods.commons.vo.LivegoodsResult;

public interface BuyactionService {

    //商品购买
    LivegoodsResult buy(String id,String username);
}
