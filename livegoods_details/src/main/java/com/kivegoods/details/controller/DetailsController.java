package com.kivegoods.details.controller;

import com.jyj.livegoods.pojo.Item;
import com.kivegoods.details.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class DetailsController {

    @Autowired
    private DetailsService detailsService;

    @GetMapping("/details")
    public Item showDetails(String id){
        return detailsService.showDetails(id);
    }
}
