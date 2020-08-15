package com.livegoods.login.dao;

import com.jyj.livegoods.pojo.LoginLog;

public interface LoginLogDao {
    //添加用户的信息到MongoDB，每次登陆都是新增=修改
    void insert(LoginLog loginLog);
}
