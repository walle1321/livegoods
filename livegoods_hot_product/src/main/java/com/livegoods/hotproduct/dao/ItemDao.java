package com.livegoods.hotproduct.dao;


import com.jyj.livegoods.pojo.Item;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;


public interface ItemDao {
    //查询所有的商品，找出热门的来
    List<Item> selectItem(Query query);

}
