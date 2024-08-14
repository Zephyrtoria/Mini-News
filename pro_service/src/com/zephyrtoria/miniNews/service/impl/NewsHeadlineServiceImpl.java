package com.zephyrtoria.miniNews.service.impl;

import com.zephyrtoria.miniNews.common.Result;
import com.zephyrtoria.miniNews.dao.NewsHeadlineDao;
import com.zephyrtoria.miniNews.dao.impl.NewsHeadlineDaoImpl;
import com.zephyrtoria.miniNews.pojo.vo.HeadlineQueryVo;
import com.zephyrtoria.miniNews.service.NewsHeadlineService;

import java.util.HashMap;
import java.util.Map;

public class NewsHeadlineServiceImpl implements NewsHeadlineService {
    private final NewsHeadlineDao newsHeadlineDao = new NewsHeadlineDaoImpl();

    @Override
    public Result findPage(HeadlineQueryVo headlineQueryVo) {
        /*
        "data":{
    	"pageInfo":{
    		"pageData":[{
    		    	"hid":"1",                     // 新闻id
    				"title":"尚硅谷宣布 ... ...",   // 新闻标题
    				"type":"1",                    // 新闻所属类别编号
    				"pageViews":"40",              // 新闻浏览量
    				"pastHours":"3" ,              // 发布时间已过小时数
    				"publisher":"1"                // 发布用户ID
    		}],  // 本页的数据，结构和HeadlinePageVo一样
			"pageNum":1,    //页码数
			"pageSize":10,  // 页大小
			"totalPage":20, // 总页数
			"totalSize":200 // 总记录数
		    }  // pageInfo
		} // data
        *
        *
        * */
        Map<String, Object> data = new HashMap();
        Map<String, Object> pageInfo = new HashMap();
        pageInfo.put("pageNum", headlineQueryVo.getPageNum());
        pageInfo.put("pageSize", headlineQueryVo.getPageSize());
        pageInfo.put("pageData", newsHeadlineDao.findPageList(headlineQueryVo));

        int totalSize = newsHeadlineDao.findPageCount(headlineQueryVo);
        int totalPage = (totalSize - 1) / headlineQueryVo.getPageSize() + 1;
        pageInfo.put("totalSize", totalSize);
        pageInfo.put("totalPage", totalPage);

        data.put("pageInfo", pageInfo);
        return Result.ok(data);
    }
}
