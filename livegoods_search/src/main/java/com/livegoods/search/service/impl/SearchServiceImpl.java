package com.livegoods.search.service.impl;

import com.livegoods.commons.vo.LivegoodsResult;
import com.livegoods.search.dao.ESDao;
import com.livegoods.search.pojo.Item4ES;
import com.livegoods.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private ESDao esDao;

    @Override
    public LivegoodsResult searchForPage(String city, String content, int page, int rows) {
        AggregatedPage<Item4ES> item4ES = esDao.queryForPage(city, content, page, rows);
        LivegoodsResult result = new LivegoodsResult();
        result.setData(item4ES.getContent());
        if (page<item4ES.getTotalPages()-1){
            result.setHasMore(true);
        }else {
            result.setHasMore(false);
        }

        return result;
    }
}
