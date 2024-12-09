package com.linebead.service;

import com.linebead.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linebead.utils.Result;

/**
 * @author liushengtao
 * @description 针对表【news_user】的数据库操作Service
 * @createDate 2024-12-08 21:24:11
 */
public interface UserService extends IService<User> {

    Result login(User user);

    Result getUserInfo(String token);

    Result checkUsername(String username);

    Result regist(User user);
}
