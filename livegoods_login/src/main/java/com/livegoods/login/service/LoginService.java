package com.livegoods.login.service;

import com.livegoods.commons.vo.LivegoodsResult;

public interface LoginService {
    /**
     * 验证码操作
     * @param iphoneNumber
     * @return
     */
    LivegoodsResult getVerificationCode(long iphoneNumber);

    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     */
    LivegoodsResult login(String username,String password);
}
