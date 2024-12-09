package com.linebead.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linebead.pojo.Type;
import com.linebead.service.TypeService;
import com.linebead.mapper.TypeMapper;
import com.linebead.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liushengtao
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-12-08 21:24:11
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{
    @Autowired
    private TypeMapper typeMapper;

    /**
     * 查询所有类别数据
     * @return
     */
    @Override
    public Result findAllTypes() {

        List<Type> types = typeMapper.selectList(null);

        return Result.ok(types);
    }
}




