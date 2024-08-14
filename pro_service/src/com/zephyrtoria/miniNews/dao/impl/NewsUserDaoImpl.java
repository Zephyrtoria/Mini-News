package com.zephyrtoria.miniNews.dao.impl;

import com.zephyrtoria.miniNews.dao.BaseDao;
import com.zephyrtoria.miniNews.dao.NewsUserDao;
import com.zephyrtoria.miniNews.pojo.NewsUser;
import com.zephyrtoria.miniNews.util.MD5Util;

import java.util.List;

public class NewsUserDaoImpl extends BaseDao implements NewsUserDao {
    @Override
    public NewsUser findByUsername(String username) {
        String sql = """
                select
                    uid,
                    username,
                    user_pwd userPwd,
                    nick_name nickName
                from
                    news_user
                where
                    username = ?
                """;
        List<NewsUser> list = baseQuery(NewsUser.class, sql, username);
        if (null != list && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public NewsUser findByUid(Integer userId) {
        String sql = """
                select
                    uid,
                    username,
                    user_pwd userPwd,
                    nick_name nickName
                from
                    news_user
                where
                    uid = ?
                """;
        List<NewsUser> list = baseQuery(NewsUser.class, sql, userId);
        if (null != list && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Integer insertOneItem(NewsUser newsUser) {
        String sql = """
                insert into
                    news_user
                values(
                    DEFAULT,
                    ?,
                    ?,
                    ?
                )
                """;
        return baseUpdate(sql, newsUser.getUsername(), newsUser.getUserPwd(), newsUser.getNickName());
    }
}
