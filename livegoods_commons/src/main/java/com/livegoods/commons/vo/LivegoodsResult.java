package com.livegoods.commons.vo;

import lombok.Data;

@Data
public class LivegoodsResult {
    //正确返回200.错误500
    private int status;
    //热门商品所需的返回结果
    private Object results;
    private String msg;
    private Object data;
    //分页返回结果，是否还有更多对象
    private boolean hasMore;


    //只返回status=200的静态方法
    public static LivegoodsResult ok(){
        LivegoodsResult result = new LivegoodsResult();
        result.setStatus(200);
        return result;
    }

    //带返回参数的
    public static LivegoodsResult ok(Object results){
        LivegoodsResult result = new LivegoodsResult();
        result.setStatus(200);
        result.setResults(results);
        return result;
    }

    //带返回参数的
    public static LivegoodsResult okData(Object data){
        LivegoodsResult result = new LivegoodsResult();
        result.setStatus(200);
        result.setData(data);
        return result;
    }

    //错误返回
    public static LivegoodsResult error(String msg){
        LivegoodsResult result = new LivegoodsResult();
        result.setStatus(500);
        result.setMsg(msg);
        return result;
    }
}
