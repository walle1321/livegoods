package com.livegoods.login.redisdao;

public interface LoginRedisDao {
    /**
     * 往redis中添加数据
     * @param key  手机号
     * @param value  验证码
     */
    void set(long key,int value);

    /**
     * 从redis中获取数据
     * @param key 用户手机号
     * @return 验证码
     */
    Integer get(long key);

    /**
     * 在redis中删除数据
     * @param key
     */
    void delete(long key);
}
