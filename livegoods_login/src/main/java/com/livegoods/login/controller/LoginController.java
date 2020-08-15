package com.livegoods.login.controller;

import com.livegoods.commons.vo.LivegoodsResult;
import com.livegoods.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 返回验证码，验证码储存在Redis中，两分钟有效时间
     * @param phone
     * @return
     */
    @PostMapping("/sendyzm")
    private LivegoodsResult getCode( long phone) {
        return loginService.getVerificationCode(phone);
    }

    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public LivegoodsResult login(String username,String password){
        return loginService.login(username, password);
    }
}
