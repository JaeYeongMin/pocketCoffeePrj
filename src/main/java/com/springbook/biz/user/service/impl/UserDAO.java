package com.springbook.biz.user.service.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.product.service.impl.ProductDAO;





// User DAO
@Repository("userDAO")     //context 설정파일에 등록한다.
public class UserDAO {
	@Autowired
	private SqlSessionTemplate mybatis;


    // 로그인 YN 업데이트
    public HashMap<String, Object> updateLoginYN(HashMap<String, Object> paramMap) {
    	HashMap<String, Object> resultMap = new HashMap<String, Object>();
    	
    	int result = mybatis.update("UserSQL.updateLoginYN", paramMap);
    	
    	if(result < 1) {
    		resultMap.put("RETN_MENT", "로그인 실패");
    		resultMap.put("RETN_CODE", "400");
    	}else {
    		resultMap.put("RETN_MENT", "로그인 성공");
    		resultMap.put("RETN_CODE", "200");
    	}
    	
        return resultMap;
    }
    
    // 계정정보 검색
    public HashMap<String, Object> selectUserInfoOne(HashMap<String, Object> paramMap) {
        return  mybatis.selectOne("UserSQL.selectUserInfoOne", paramMap);
    }
    
    
    
    
    
    // 계정정보 수정
    public HashMap<String, Object> updateUserInfo(HashMap<String, Object> paramMap) {
    	HashMap<String, Object> resultMap = new HashMap<String, Object>();
    	
    	int result = mybatis.update("UserSQL.updateUserInfo", paramMap);
    	
    	if(result < 1) {
    		resultMap.put("RETN_MENT", "정보 수정 실패");
    		resultMap.put("RETN_CODE", "400");
    	}else {
    		resultMap.put("RETN_MENT", "정보 수정 성공");
    		resultMap.put("RETN_CODE", "200");
    	}
    	
        return resultMap;
    }
    
}
