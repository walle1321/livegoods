package com.livegoods.comment.controller;

import com.livegoods.comment.service.CommentService;
import com.livegoods.commons.vo.LivegoodsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    //查询商品评论
    @GetMapping("/comment")
    public LivegoodsResult showComment(@RequestParam(value = "id") String itemId, int page,@RequestParam(defaultValue = "4") int rows){
        return commentService.showComment(itemId, page, rows);
    }


    //新增商品评论
    @PostMapping("/feelback")
    public LivegoodsResult feelback(String orderId,String feelback){
        return commentService.insertComment(orderId,feelback);
    }
}
