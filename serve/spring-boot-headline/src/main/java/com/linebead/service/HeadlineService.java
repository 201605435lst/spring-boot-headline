package com.linebead.service;

import com.linebead.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linebead.pojo.vo.PortalVo;
import com.linebead.utils.Result;

/**
* @author liushengtao
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-12-08 21:24:11
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewsPage(PortalVo portalVo);

    Result showHeadlineDetail(Integer hid);

    /**
     * 发布头条方法
     * @param headline
     * @return
     */
    Result publish(Headline headline,String token);

    /**
     * 修改头条数据
     * @param headline
     * @return
     */
    Result updateData(Headline headline);
}
