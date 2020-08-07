package com.jyj.livegoods.pojo;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * 轮播图片
 */
@Data
@NoArgsConstructor
public class Banner {
    //主键
    private String id;
    //图片地址
    private String url;
    //创建日期
    private Date createTime;

}
