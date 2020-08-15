package com.livegoods.login.pojo;

import lombok.Data;

@Data
public class VerificationCode {
    //用户手机号
    private long phoneNumber;
    //验证码
    private int  VerificationCode;
}
