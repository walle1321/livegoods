package com.livegoods.recommedation.controller;

import com.livegoods.commons.vo.LivegoodsResult;
import com.livegoods.recommedation.service.RecommedationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RecommedationController {

    @Autowired
    private RecommedationService recommedationService;

    /**
     * 展示推荐商品
     * @param city
     * @return
     */
    @GetMapping("/recommendation")
    public LivegoodsResult showRecommedation(String city){
        return recommedationService.showRecommedation(city);
    }

}
