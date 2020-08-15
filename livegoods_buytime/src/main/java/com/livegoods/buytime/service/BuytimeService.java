package com.livegoods.buytime.service;


import java.util.Map;

public interface BuytimeService {

    //返回可以预定的时间
    Map<String,Object> showBuytime(String id);

}
