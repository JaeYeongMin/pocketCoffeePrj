package com.springbook.biz.user.service.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springbook.biz.product.service.impl.ProductDAO;
import com.springbook.biz.user.service.UserService;



@Component("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;


    // 로그인 yn 업데이트
    public HashMap<String, Object> updateLoginYN(HashMap<String, Object> paramMap) {
    	return userDAO.updateLoginYN(paramMap);
    }
    
    
    // 계정정보
    public HashMap<String, Object> selectUserInfoOne(HashMap<String, Object> paramMap) {
    	return userDAO.selectUserInfoOne(paramMap);
    }
    
    // 계정수정
    public HashMap<String, Object> updateUserInfo(HashMap<String, Object> paramMap) {
    	return userDAO.updateUserInfo(paramMap);
    }
    

}
