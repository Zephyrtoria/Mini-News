package com.zephyrtoria.miniNews.service;

import com.zephyrtoria.miniNews.common.Result;
import com.zephyrtoria.miniNews.pojo.vo.HeadlineQueryVo;

public interface NewsHeadlineService {
    /**
     * 根据传入的页面信息进行分页查询
     * @param headlineQueryVo 页面信息以HeadlineQueryVo形式传入
     * @return 将返回的头条封装到Result对象中进行返回
     */
    Result findPage(HeadlineQueryVo headlineQueryVo);
}
