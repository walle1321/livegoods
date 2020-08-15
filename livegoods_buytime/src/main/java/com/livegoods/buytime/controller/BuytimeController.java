package com.livegoods.buytime.controller;

import com.livegoods.buytime.service.BuytimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin
public class BuytimeController {

    @Autowired
    private BuytimeService buytimeService;

    /**
     * 通过id查询商品预定时间
     * @param id
     * @return
     */
    @GetMapping("/buytime")
    public Map<String,Object> showBuytime(String id){
        return buytimeService.showBuytime(id);
    }

}
