package org.cheng.lemon.controllers;


import com.alibaba.fastjson.JSON;
import org.cheng.lemon.entity.RequestParam;
import org.cheng.lemon.entity.UserInfo;
import org.cheng.lemon.service.UserService;
import org.cheng.lemon.unit.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Controller
@RequestMapping("/lemon")
public class UserController {


    @Autowired
    UserService userService;


//    //登录
//    @RequestMapping("/login")
//    @ResponseBody
//    public Boolean login(@RequestBody RequestParam param, HttpServletRequest request) throws IOException {
//
//    }

    //获取用户列表
    @RequestMapping("/list")
    @ResponseBody
    public String getUserList(@RequestBody RequestParam param, HttpServletRequest request) throws IOException {

        //请求日志
        Logger.recordRequestOperate(param, request);

        UserInfo userInfo = JSON.parseObject(JSON.toJSONString(param.getData()),UserInfo.class);

        String result = userService.getLoginService(userInfo);
        return result;
    }


}
