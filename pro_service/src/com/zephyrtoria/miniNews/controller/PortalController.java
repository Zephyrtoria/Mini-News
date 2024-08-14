package com.zephyrtoria.miniNews.controller;

import com.zephyrtoria.miniNews.common.Result;
import com.zephyrtoria.miniNews.pojo.NewsType;
import com.zephyrtoria.miniNews.pojo.vo.HeadlineQueryVo;
import com.zephyrtoria.miniNews.service.NewsHeadlineService;
import com.zephyrtoria.miniNews.service.NewsTypeService;
import com.zephyrtoria.miniNews.service.impl.NewsHeadlineServiceImpl;
import com.zephyrtoria.miniNews.service.impl.NewsTypeServiceImpl;
import com.zephyrtoria.miniNews.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * 门户控制器
 * 处理那些不需要登录、不需要做增删改的门户页的请求
 */
@WebServlet("/portal/*")
public class PortalController extends BaseController{
    private final NewsTypeService newsTypeService = new NewsTypeServiceImpl();
    private final NewsHeadlineService newsHeadlineService = new NewsHeadlineServiceImpl();

    /**
     * 查询所有头条类型，装入Result响应给客户端
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findAllTypes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<NewsType> newsTypeList =  newsTypeService.findAll();
        WebUtil.writeJson(resp, Result.ok(newsTypeList));
    }

    /**
     * 根据分页查询头条信息的接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findNewsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收请求中的参数
        HeadlineQueryVo headlineQueryVo = WebUtil.readJson(req, HeadlineQueryVo.class);

        // 调用Service层方法，进行分页查询
        Result result = newsHeadlineService.findPage(headlineQueryVo);

        // 响应结果
        WebUtil.writeJson(resp, result);
    }
}
