package com.linebead.controller;

import com.linebead.pojo.User;
import com.linebead.service.UserService;
import com.linebead.utils.JwtHelper;
import com.linebead.utils.Result;
import com.linebead.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liushengtao
 * @description 描述
 * @date 2024年12月08日22:16
 */

@RestController
@RequestMapping("/user")
@CrossOrigin//解决跨域
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return userService.login(user);
    }

    /*根据token获取用户*/
    @GetMapping("/getUserInfo")
    public Result getUserInfo(@RequestHeader String token) {
        return userService.getUserInfo(token);
    }

    /*验证用户名的是否可用*/
    @PostMapping("/checkUserName")
    public Result checkUsername(String username) {
        return userService.checkUsername(username);
    }

    @PostMapping("regist")
    public Result regist(@RequestBody  User user){
        Result result = userService.regist(user);
        return result;
    }

    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token){

        boolean expiration = jwtHelper.isExpiration(token);

        if (expiration){
            //已经过期了
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }
        return Result.ok(null);
    }

}
