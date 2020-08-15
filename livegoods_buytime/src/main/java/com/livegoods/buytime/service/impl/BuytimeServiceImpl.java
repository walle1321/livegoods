package com.livegoods.buytime.service.impl;

import com.livegoods.buytime.dao.BuytimeDao;
import com.livegoods.buytime.service.BuytimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class BuytimeServiceImpl implements BuytimeService {

    @Autowired
    private BuytimeDao buytimeDao;

    @Override
    public Map<String,Object> showBuytime(String id) {

        Date time = buytimeDao.findById(id);
        Map<String,Object> map = new HashMap<>();
        map.put("status",200);
        map.put("time",time.getTime());
        return map;
    }
}
