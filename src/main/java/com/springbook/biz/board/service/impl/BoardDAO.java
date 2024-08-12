package com.springbook.biz.board.service.impl;


import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;






// Board DAO
@Repository("boardDAO")     //context 설정파일에 등록한다.
public class BoardDAO {
	@Autowired
	
	private SqlSessionTemplate mybatis;

	
	
    // 게시판 리스트
    public List<HashMap<String, Object>> getBoardList(HashMap<String, Object> paramMap) {
    	
        return mybatis.selectList("BoardSQL.getBoardList", paramMap);
    }
    	
	
	

    // 로그인 YN 업데이트
    public HashMap<String, Object> updateLoginYN(HashMap<String, Object> paramMap) {
    	HashMap<String, Object> resultMap = new HashMap<String, Object>();
    	
    	int result = mybatis.update("BoardSQL.updateLoginYN", paramMap);
    	
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
        return  mybatis.selectOne("BoardSQL.selectUserInfoOne", paramMap);
    }
    
    
    
    
    
    // 계정정보 수정
    public HashMap<String, Object> updateUserInfo(HashMap<String, Object> paramMap) {
    	HashMap<String, Object> resultMap = new HashMap<String, Object>();
    	
    	int result = mybatis.update("BoardSQL.updateUserInfo", paramMap);
    	
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
