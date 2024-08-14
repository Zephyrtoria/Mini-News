package com.zephyrtoria.miniNews.controller;

import com.zephyrtoria.miniNews.common.Result;
import com.zephyrtoria.miniNews.pojo.NewsUser;
import com.zephyrtoria.miniNews.service.NewsUserService;
import com.zephyrtoria.miniNews.service.impl.NewsUserServiceImpl;
import com.zephyrtoria.miniNews.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/*")
public class NewsUserController extends BaseController {
    private final NewsUserService newsUserService = new NewsUserServiceImpl();

    /**
     * 处理登录表单提交的业务接口的实现
     *
     * @param req  使用POST方式请求
     * @param resp 返回响应示例
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收用户名和密码
        NewsUser newsUser = WebUtil.readJson(req, NewsUser.class);

        // 调用Service层方法
        Result result = newsUserService.login(newsUser);

        // 返回结果
        WebUtil.writeJson(resp, result);
    }

    /**
     * 根据token口令获得用户信息的接口
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求中的token
        String token = req.getHeader("token");

        // 调用Service层方法校验token
        Result result = newsUserService.checkToken(token);

        // 返回结果
        WebUtil.writeJson(resp, result);
    }

    /**
     * 处理注册表单提交的业务接口的实现
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 读取数据
        NewsUser newsUser = WebUtil.readJson(req, NewsUser.class);

        // 调用Service层方法进行查询
        Result result = newsUserService.register(newsUser);

        // 返回结果
        WebUtil.writeJson(resp, result);
    }

    /**
     * 查询数据库中用户名是否被占用的接口实现
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 读取数据
        String username = req.getParameter("username");

        // 调用Service层方法进行查询
        Result result = newsUserService.checkUserName(username);

        // 返回结果
        WebUtil.writeJson(resp, result);
    }


}
