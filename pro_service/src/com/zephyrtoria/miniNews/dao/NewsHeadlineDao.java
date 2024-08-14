package com.zephyrtoria.miniNews.dao;

import com.zephyrtoria.miniNews.pojo.vo.HeadlinePageVo;
import com.zephyrtoria.miniNews.pojo.vo.HeadlineQueryVo;

import java.util.List;

public interface NewsHeadlineDao {
    /**
     * 根据传入参数在news_headline数据库中进行分页查询
     * @param headlineQueryVo 查询相关参数以HeadlineQueryVo形式入参
     * @return 查询到的结果以List形式返回
     */
    List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo);

    /**
     * 根据传入参数在news_headline数据库中进行分页查询，进行计数
     * @param headlineQueryVo 查询相关参数以HeadlineQueryVo形式入参
     * @return 返回查询到的头条个数
     */
    int findPageCount(HeadlineQueryVo headlineQueryVo);
}
