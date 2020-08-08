package com.livegoods.recommedation.service;

import com.livegoods.commons.vo.LivegoodsResult;

public interface RecommedationService {
    /**
     * 展示推荐商品房
     * @param city
     * @return
     */
    LivegoodsResult showRecommedation(String city);
}
