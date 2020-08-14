package com.livegoods.comment.controller;

import com.livegoods.comment.service.CommentService;
import com.livegoods.commons.vo.LivegoodsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comment")
    public LivegoodsResult showComment(@RequestParam(value = "id") String itemId, int page,@RequestParam(defaultValue = "4") int rows){
        return commentService.showComment(itemId, page, rows);
    }
}
