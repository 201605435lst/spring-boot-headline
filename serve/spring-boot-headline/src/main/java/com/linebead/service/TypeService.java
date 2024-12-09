package com.linebead.service;

import com.linebead.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linebead.utils.Result;

/**
* @author liushengtao
* @description 针对表【news_type】的数据库操作Service
* @createDate 2024-12-08 21:24:11
*/
public interface TypeService extends IService<Type> {

    Result findAllTypes();
}
