package com.zephyrtoria.miniNews.service.impl;

import com.zephyrtoria.miniNews.dao.NewsTypeDao;
import com.zephyrtoria.miniNews.dao.impl.NewsTypeDaoImpl;
import com.zephyrtoria.miniNews.pojo.NewsType;
import com.zephyrtoria.miniNews.service.NewsTypeService;

import java.util.List;

public class NewsTypeServiceImpl implements NewsTypeService {
    private final NewsTypeDao newsTypeDao = new NewsTypeDaoImpl();
    @Override
    public List<NewsType> findAll() {
        return newsTypeDao.findAll();
    }
}
