package com.livegoods.recommedation.dao;

import com.jyj.livegoods.pojo.Item;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface RecommedationDao {
    /**
     * 查询推荐商品
     * @param query
     * @return
     */
    List<Item> select(Query query);
}
