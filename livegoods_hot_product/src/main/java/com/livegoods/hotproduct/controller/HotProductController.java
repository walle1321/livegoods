package com.livegoods.hotproduct.controller;

import com.livegoods.commons.vo.LivegoodsResult;
import com.livegoods.hotproduct.service.HotProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HotProductController {

    @Autowired
    private HotProductService hotProductService;

    /**
     * 展示热门商品
     * @param city
     * @return
     */
    @GetMapping("/hotProduct")
    private LivegoodsResult showHotProduct(String city){
        return hotProductService.showHotProducts(city);
    }

}
