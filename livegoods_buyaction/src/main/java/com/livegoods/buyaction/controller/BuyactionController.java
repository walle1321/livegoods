package com.livegoods.buyaction.controller;

import com.livegoods.buyaction.service.BuyactionService;
import com.livegoods.commons.vo.LivegoodsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class BuyactionController {

    @Autowired
    private BuyactionService buyactionService;

    @GetMapping("/buyaction")
    public LivegoodsResult buy(String id,String user){
        return buyactionService.buy(id, user);
    }
}
