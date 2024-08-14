package com.zephyrtoria.miniNews.dao;

import com.zephyrtoria.miniNews.pojo.NewsUser;

public interface NewsUserDao {
    /**
     * 根据传入的用户名在数据库news_user中查找对应用户
     *
     * @param username 需要查找的用户名
     * @return 将查找到的用户以NewsUser对象形式返回
     */
    NewsUser findByUsername(String username);

    /**
     * 根据传入uid在数据库news_user中查找对应用户
     *
     * @param userId 需要查找的uid
     * @return 将查找到的用户以NewsUser对象形式返回
     */
    NewsUser findByUid(Integer userId);

    /**
     * 根据传入的NewsUser参数向数据库中添加用户
     * @param newsUser 需要添加的用户信息以NewsUser形式传入
     * @return 成功添加返回1；添加失败返回0
     */
    Integer insertOneItem(NewsUser newsUser);
}
