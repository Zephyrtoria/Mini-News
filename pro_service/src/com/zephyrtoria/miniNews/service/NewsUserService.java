package com.zephyrtoria.miniNews.service;

import com.zephyrtoria.miniNews.common.Result;
import com.zephyrtoria.miniNews.pojo.NewsUser;

public interface NewsUserService {
    /**
     * 进行用户登录的操作
     *
     * @param newsUser 用户信息（用户名、密码）以NewsUser的形式传递
     * @return 根据登录成功或失败的情况，将数据封装为Result返回。如果成功，需要将token放入Result中
     */
    Result login(NewsUser newsUser);

    /**
     * 比对传入的token和数据库存储的token是否对应
     *
     * @param token 需要比较的token
     * @return 将比较结果封装为Result对象返回
     */
    Result checkToken(String token);

    /**
     * 根据传入的用户名，查看数据库中是否存在该用户
     * @param username 需要查找的用户名
     * @return 如果数据库中存在该用户，则返回USERNAME_USED；若不存在该用户，则返回Result.ok()
     */
    Result checkUserName(String username);

    /**
     * 根据客户端传入的用户名、密码、昵称信息进行注册
     * @param newsUser
     * @return
     */
    Result register(NewsUser newsUser);
}
