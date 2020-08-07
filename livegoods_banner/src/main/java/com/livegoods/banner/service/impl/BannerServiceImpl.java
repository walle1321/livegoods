package com.livegoods.banner.service.impl;

import com.jyj.livegoods.pojo.Banner;
import com.livegoods.banner.dao.BannerDao;
import com.livegoods.banner.service.BannerService;
import com.livegoods.commons.vo.LivegoodsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDao bannerDao;
    @Value("${livegoods.banner.nginx.host}")
    private String nginxHost;

    @Override
    public LivegoodsResult showBanner() {
        try {
            List<String> result = new ArrayList<>();
            Query query = new Query();
            query.with(PageRequest.of(0,4, Sort.by(Sort.Direction.DESC,"createTime")));
            List<Banner> bannerList = bannerDao.selectBanner(query);
            for (Banner banner :
                    bannerList) {
                result.add(nginxHost+banner.getUrl());
            }
            return LivegoodsResult.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LivegoodsResult.error("查询轮播图出错");
    }
}
