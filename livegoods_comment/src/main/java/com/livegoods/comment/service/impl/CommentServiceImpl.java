package com.livegoods.comment.service.impl;

import com.jyj.livegoods.pojo.Comment;
import com.livegoods.comment.dao.CommentDao;
import com.livegoods.comment.service.CommentService;
import com.livegoods.commons.vo.LivegoodsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public LivegoodsResult showComment(String itemId,int page,int rows) {

        //查询出所有的数据
        Query query = new Query();
        query.addCriteria(
                new Criteria().andOperator(
                        Criteria.where("itemId").is(itemId)
                )
        );
        query.with(PageRequest.of(page,rows));
        List<Comment> comments = commentDao.selectByItemId(query);
        long count = commentDao.selectCountByItemId(itemId);
        System.out.println("查询结果"+comments+"count"+count);

        // 隐藏用户手机号。 135 1234 4321 -> 135 **** 4321
        for(Comment comment : comments){
            String username = comment.getUsername()
                    .replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
            comment.setUsername(username);
        }


//        if (count%rows==0){
//            if (count/rows>page){
//                result.setHasMore(true);
//                return result;
//            }
//        }else {
//            if (count/rows>page+1){
//                result.setHasMore(true);
//                return result;
//            }
//        }
//        result.setHasMore(false);
//        return result;
        // 总计页码数
        LivegoodsResult result = LivegoodsResult.okData(comments);
        long totalPages = ((count % rows == 0) ? (count / rows) : ((count / rows) + 1));
        if((page + 1) < totalPages) { // 有更多数据
            result.setHasMore(true);
        }else{ // 没有更多数据
            result.setHasMore(false);
        }
        return result;
    }

}
