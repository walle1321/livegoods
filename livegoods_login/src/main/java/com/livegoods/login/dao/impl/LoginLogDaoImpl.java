package com.livegoods.login.dao.impl;

import com.jyj.livegoods.pojo.LoginLog;
import com.livegoods.login.dao.LoginLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginLogDaoImpl implements LoginLogDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(LoginLog loginLog) {
        mongoTemplate.save(loginLog);
    }
}
