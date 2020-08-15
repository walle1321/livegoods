package com.livegoods.login.service.impl;

import com.jyj.livegoods.pojo.LoginLog;
import com.livegoods.commons.vo.LivegoodsResult;
import com.livegoods.login.dao.LoginLogDao;
import com.livegoods.login.redisdao.LoginRedisDao;
import com.livegoods.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRedisDao loginRedisDao;
    @Autowired
    private LoginLogDao loginLogDao;

    /**
     * 发送验证码
     * 动态生成一个长度为 4 的随机数字字符串作为验证码。
     * 保存手机号和验证码到 redis，有效时长为 2 分钟。
     *
     * 在验证码仍旧有效的时候，再次申请验证码，应该如何处理？
     * 1、 生成新的验证码，有效时间刷新为 2 分钟，覆盖旧验证码。 对客户端比较友
     好，服务端成本提高。
     * 2、 通知客户端，验证码仍旧可用，不重新发送。 对客户端来说，友好度降低；
     服务器压力降低，成本降低。
     */
    @Override
    public LivegoodsResult getVerificationCode(long iphoneNumber) {

        if (loginRedisDao.get(iphoneNumber)!=null){
            System.out.println("redis中有数据");
            return LivegoodsResult.error("两分钟内不允许重复发送验证码");
        }



        //生成随机四位数
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10));
        }
        System.out.println("验证码"+sb.toString());


        //添加到redis
        loginRedisDao.set(iphoneNumber,Integer.parseInt(String.valueOf(sb)));

        //返回结果
        LivegoodsResult result = LivegoodsResult.ok();
        result.setMsg("已发送验证码");
        return result;
    }

    /**
     * 登录， 所有的操作结果都必须记录登录日志。
     * 1、 根据手机号查询redis，获取验证码。 检查redis中是否有数据，无数据，记录日志，返回登录失败。
     * 2、 校验，用户发送的验证码和redis中记录的验证码是否匹配，如果不匹配，记录日志，返回登录失败。
     * 3、 校验验证码成功，记录日志，并删除redis中存储的验证码。 返回登录成功。
     * @param username 手机号
     * @param password 验证码
     * @return
     */
    @Override
    public LivegoodsResult login(String username, String password) {
        LoginLog loginLog = new LoginLog();
        loginLog.setLoginTime(new Date());
        loginLog.setType("1"); //直接使用验证码登陆
        loginLog.setUsername(username);

        Integer code = loginRedisDao.get(Long.parseLong(username));
        if (code==null){
            loginLog.setIsSuccess(false);
            loginLog.setMessage("验证码已过期");
            loginLogDao.insert(loginLog);
            return LivegoodsResult.error("用户名或密码错误！");
        }
        //唯一成功的时候
        if (code.equals(Integer.valueOf(password))){
            loginLog.setIsSuccess(true);
            loginLog.setMessage("登陆成功");
            loginLogDao.insert(loginLog);
            loginRedisDao.delete(Long.parseLong(username));
            return LivegoodsResult.ok("登陆成功");
        }else {
            loginLog.setIsSuccess(false);
            loginLog.setMessage("验证码不匹配");
            loginLogDao.insert(loginLog);
            return LivegoodsResult.error("用户名或密码错误！");
        }
    }
}
