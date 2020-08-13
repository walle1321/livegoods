package com.kivegoods.details.dao;

import com.jyj.livegoods.pojo.Item;

public interface DetailsDao {
    //根据主键查询
    Item selectById(String id);
}
