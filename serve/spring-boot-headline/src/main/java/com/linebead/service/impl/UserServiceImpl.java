package com.linebead.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linebead.pojo.User;
import com.linebead.service.UserService;
import com.linebead.mapper.UserMapper;
import com.linebead.utils.JwtHelper;
import com.linebead.utils.MD5Util;
import com.linebead.utils.Result;
import com.linebead.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author liushengtao
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2024-12-08 21:24:11
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 登录业务
     *
     *   1.根据账号，查询用户对象  - loginUser
     *   2.如果用户对象为null，查询失败，账号错误！ 501
     *   3.对比，密码 ，失败 返回503的错误
     *   4.根据用户id生成一个token, token -> result 返回
     * @param user
     * @return
     */
    @Override
    public Result login(User user) {

        /*查询*/
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        User loginUser = userMapper.selectOne(queryWrapper);

        if(loginUser == null){
           return  Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }

        if(!StringUtils.isEmpty(user.getUserPwd()) &&  MD5Util.encrypt(user.getUserPwd()).equals(loginUser.getUserPwd())){
            /*创建token*/
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));

            Map<String, Object> map=new HashMap<>();
            map.put("token",token);
            return Result.ok(map);
        }



        return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }


    /**
     * 根据token获取用户数据
     *
     *  1. token是否在有效期
     *  2. 根据token解析userId
     *  3. 根据用户id查询用数据
     *  4. 去掉密码，封装result结果返回即可
     *
     * @param token
     * @return
     */

    @Override
    public Result getUserInfo(String token) {

        /*判断token的有效性*/
        if(StringUtils.isEmpty(token) || jwtHelper.isExpiration(token)){
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }

        /*更具id查找用户*/
        Long userId = jwtHelper.getUserId(token);
        if(userId == null){
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }

        final User user = userMapper.selectById(userId);

        user.setUserPwd(null);
        Map<String, Object> map=new HashMap<>();
        map.put("user",user);
        return Result.ok(map);

    }

    /**
     * 检查账号是否可用
     *   1.根据账号进行count查询
     *   2.count == 0 可用
     *   3.count > 0 不可用
     * @param username 账号
     * @return
     */
    @Override
    public Result checkUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);

        final Long l = userMapper.selectCount(queryWrapper);
        if(l == 0){
            return Result.ok(null);

        }else {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
    }

    /**
     * 注册业务
     *  1.依然检查账号是否已经被注册
     *  2.密码加密处理
     *  3.账号数据保存
     *  4.返回结果
     * @param user
     * @return
     */
    @Override
    public Result regist(User user) {
        LambdaQueryWrapper<User> queryWrapper
                = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,user.getUsername());
        Long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }

        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));

        userMapper.insert(user);

        return Result.ok(null);
    }
}




