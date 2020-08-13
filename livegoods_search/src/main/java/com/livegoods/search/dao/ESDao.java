package com.livegoods.search.dao;

import com.livegoods.search.pojo.Item4ES;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;

import java.util.List;

public interface ESDao {

    //must确定城市条件，should搜索关键字，分页。
    AggregatedPage<Item4ES> queryForPage(String city, String content, int page, int rows);

    //批量新增
    void insertBatchItem(List<Item4ES> item4ESList);

    //往ES中单个新增
    void insertItem(Item4ES item4ES);
}
