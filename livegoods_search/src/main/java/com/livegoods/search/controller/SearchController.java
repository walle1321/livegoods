package com.livegoods.search.controller;

import com.livegoods.commons.vo.LivegoodsResult;
import com.livegoods.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public LivegoodsResult searchForPage(String city, String content,int page,@RequestParam(defaultValue = "3") int rows){
        return searchService.searchForPage(city, content, page, rows);
    }
}
