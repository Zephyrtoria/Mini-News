package com.zephyrtoria.miniNews.controller;

import jakarta.servlet.annotation.WebServlet;

/**
 * 门户控制器
 * 处理那些不需要登录、不需要做增删改的门户页的请求
 */
@WebServlet("/portal/*")
public class PortalController extends BaseController{
}
