package com.livegoods.banner.dao;

import com.jyj.livegoods.pojo.Banner;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface BannerDao {
    /**
     * 通过条件查询轮播图
     * @param query
     * @return
     */
    List<Banner> selectBanner(Query query);
}
