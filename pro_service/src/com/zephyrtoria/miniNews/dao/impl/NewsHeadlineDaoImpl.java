package com.zephyrtoria.miniNews.dao.impl;

import com.zephyrtoria.miniNews.dao.BaseDao;
import com.zephyrtoria.miniNews.dao.NewsHeadlineDao;
import com.zephyrtoria.miniNews.pojo.vo.HeadlinePageVo;
import com.zephyrtoria.miniNews.pojo.vo.HeadlineQueryVo;

import java.util.ArrayList;
import java.util.List;

public class NewsHeadlineDaoImpl extends BaseDao implements NewsHeadlineDao {
    /*
        需要查询的信息：
            private Integer hid;
            private String title;
            private Integer type;
            private Integer pageViews;
            private Long pastHours;
            private Integer publisher;
        查询条件：
            private String keyWords;
            private Integer type;  判断是否非零
            private Integer pageNum;
            private Integer pageSize;
    */
    @Override
    public List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo) {
        String sql = """
                select
                    hid,
                    title,
                    type,
                    page_views pageViews,
                    TIMESTAMPDIFF(HOUR, create_time, now()) pastHours,
                    publisher
                from
                    news_headline
                where
                    is_deleted = 0
                """;

        // 根据headlineQueryVo进行查询条件的设置
        List params = new ArrayList();  // 因为需要填充的参数个数不一定，所以选用集合
        if (headlineQueryVo.getType() != 0) {
            sql = sql.concat(" and type = ? ");  // 注意前后都要留空格
            params.add(headlineQueryVo.getType());
        }
        if (headlineQueryVo.getKeyWords() != null && !"".equals(headlineQueryVo.getKeyWords())) {
            sql = sql.concat(" and title like ? ");
            params.add("%" + headlineQueryVo.getKeyWords() + "%");  // like需要拼接%
        }
        sql = sql.concat(" order by pastHours ASC, page_views DESC ");  // 根据发布时间升序排序，浏览量降序排序。注意order by与where有位置要求
        sql = sql.concat(" limit ?, ? ");  // 第一个?表示已经取了多少记录，第二个?表示接下来要取多少条记录
        params.add((headlineQueryVo.getPageNum() - 1) * headlineQueryVo.getPageSize());
        params.add(headlineQueryVo.getPageSize());
        return baseQuery(HeadlinePageVo.class, sql, params.toArray());  // 需要把params转成数组传递
    }

    @Override
    public int findPageCount(HeadlineQueryVo headlineQueryVo) {
        String sql = """
                select
                    count(1)
                from
                    news_headline
                where
                    is_deleted = 0
                """;

        // 根据headlineQueryVo进行查询条件的设置
        List params = new ArrayList();  // 因为需要填充的参数个数不一定，所以选用集合
        if (headlineQueryVo.getType() != 0) {  // type = 0时为主页，不需要进行筛选
            sql = sql.concat(" and type = ? ");  // 注意前后都要留空格
            params.add(headlineQueryVo.getType());
        }
        if (headlineQueryVo.getKeyWords() != null && !"".equals(headlineQueryVo.getKeyWords())) {
            sql = sql.concat(" and title like ? ");
            params.add("%" + headlineQueryVo.getKeyWords() + "%");  // like需要拼接%
        }
        return baseQueryObject(Long.class, sql, params.toArray()).intValue();
    }
}
