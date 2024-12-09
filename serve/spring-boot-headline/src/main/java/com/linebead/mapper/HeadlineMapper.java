package com.linebead.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.linebead.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linebead.pojo.vo.PortalVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author liushengtao
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-12-08 21:24:11
* @Entity com.linebead.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {

    @MapKey("headline")
    IPage<Map> selectMyPage(IPage<Map> page, @Param("portalVo") PortalVo portalVo);

    Map queryDetailMap(Integer hid);
}




