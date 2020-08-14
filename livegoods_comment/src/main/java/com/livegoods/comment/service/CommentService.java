package com.livegoods.comment.service;

import com.livegoods.commons.vo.LivegoodsResult;

public interface CommentService {

    //展示商品评论
    LivegoodsResult showComment(String itemId,int page,int rows);

}
