package com.zephyrtoria.miniNews.service.impl;

import com.zephyrtoria.miniNews.common.Result;
import com.zephyrtoria.miniNews.common.ResultCodeEnum;
import com.zephyrtoria.miniNews.dao.NewsUserDao;
import com.zephyrtoria.miniNews.dao.impl.NewsUserDaoImpl;
import com.zephyrtoria.miniNews.pojo.NewsUser;
import com.zephyrtoria.miniNews.service.NewsUserService;
import com.zephyrtoria.miniNews.util.JwtHelper;
import com.zephyrtoria.miniNews.util.MD5Util;

import java.util.HashMap;
import java.util.Map;

public class NewsUserServiceImpl implements NewsUserService {
    private final NewsUserDao newsUserDao = new NewsUserDaoImpl();

    @Override
    public Result login(NewsUser newsUser) {
        NewsUser findUser = newsUserDao.findByUsername(newsUser.getUsername());
        Result result = null;
        if (null == findUser) {
            result = Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        } else if (!MD5Util.encrypt(newsUser.getUserPwd()).equalsIgnoreCase(findUser.getUserPwd())) {
            result = Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
        } else {
            Map<String, Object> data = new HashMap();
            data.put("token", JwtHelper.createToken(findUser.getUid().longValue()));
            result = Result.ok(data);
        }

        return result;
    }

    @Override
    public Result checkToken(String token) {
        // 通过校验，查询用户信息放入Result
        if (null != token && !("".equals(token))) {
            if (!JwtHelper.isExpiration(token)) {
                Integer userId = JwtHelper.getUserId(token).intValue();
                NewsUser newsUser = newsUserDao.findByUid(userId);
                if (null != newsUser) {
                    Map data = new HashMap();
                    newsUser.setUserPwd("");
                    data.put("loginUser", newsUser);
                    return Result.ok(data);
                }
            }
        }
        // 未通过校验，响应code504
        return Result.build(null, ResultCodeEnum.NOT_LOGIN);
    }

    @Override
    public Result checkUserName(String username) {
        NewsUser findUser = newsUserDao.findByUsername(username);
        if (findUser != null) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        return Result.ok(null);
    }

    @Override
    public Result register(NewsUser newsUser) {
        NewsUser findUser = newsUserDao.findByUsername(newsUser.getUsername());
        if (findUser != null) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        newsUser.setUserPwd(MD5Util.encrypt(newsUser.getUserPwd()));
        newsUserDao.insertOneItem(newsUser);
        return Result.ok(null);
    }
}
