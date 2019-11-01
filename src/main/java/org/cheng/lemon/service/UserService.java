package org.cheng.lemon.service;


import org.cheng.lemon.dao.UserDao;
import org.cheng.lemon.entity.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    public String getLoginService (UserInfo userInfo) {

        String resultDao = userDao.getUserListDao(userInfo);
        return resultDao;
    }

}
