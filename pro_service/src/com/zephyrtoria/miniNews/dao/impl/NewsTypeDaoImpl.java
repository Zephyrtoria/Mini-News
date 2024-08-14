package com.zephyrtoria.miniNews.dao.impl;

import com.zephyrtoria.miniNews.dao.BaseDao;
import com.zephyrtoria.miniNews.dao.NewsTypeDao;
import com.zephyrtoria.miniNews.pojo.NewsType;

import java.util.List;

public class NewsTypeDaoImpl extends BaseDao implements NewsTypeDao {
    @Override
    public List<NewsType> findAll() {
        String sql = "select tid, tname from news_type";
        return baseQuery(NewsType.class, sql);
    }
}
