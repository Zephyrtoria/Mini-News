package com.zephyrtoria.miniNews.dao;

import com.zephyrtoria.miniNews.pojo.NewsType;

import java.util.List;

public interface NewsTypeDao {
    /**
     * 从news_type数据库中读取所有的头条类型的方法
     * @return 将读取的所有头条类型以<NewsType>的形式返回
     */
    List<NewsType> findAll();
}
