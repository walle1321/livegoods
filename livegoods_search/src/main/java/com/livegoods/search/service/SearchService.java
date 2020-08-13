package com.livegoods.search.service;

import com.livegoods.commons.vo.LivegoodsResult;

public interface SearchService {

    /**
     * 商品搜索
     * @param city
     * @param content
     * @param page
     * @param rows
     * @return
     */
    LivegoodsResult searchForPage(String city, String content, int page, int rows);
}
