package com.springbook.biz.user.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;


@Component("userService")
public class UserServiceImpl implements UserService {
    
    @Resource(name="userDAO")
    private UserDAO userDAO;


    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    
    public UserVO getUser(UserVO vo) {
        return userDAO.getUser(vo);
    }

    
    

}
