package com.livegoods.banner.controller;

import com.livegoods.banner.service.BannerService;
import com.livegoods.commons.vo.LivegoodsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class BannerController {

    @Autowired
    private BannerService  bannerService;

    @GetMapping("/banner")
    public LivegoodsResult showBanner(){
        return bannerService.showBanner();
    }
}
