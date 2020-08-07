package com.livegoods.hotproduct.service;

import com.livegoods.commons.vo.LivegoodsResult;

public interface HotProductService {

    /**
     * 展示热门商品
     * 查询的返回结果，热销商品的数量必须是4。
     * 查询条件所在城市的热销商品数量大于4的时候，只查询销量排序的前4位商品。
     * 如果条件所在城市的热销商品数量小于4的时候，从其他的城市热销商品中查询销量排序考前的补足。
     * @return
     */
    LivegoodsResult showHotProducts(String city);

}
