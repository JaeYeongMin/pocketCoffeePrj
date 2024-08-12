package com.springbook.biz.board.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springbook.biz.board.service.BoardService;



@Component("boardService")
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardDAO boardDAO;


    // 로그인 yn 업데이트
    public HashMap<String, Object> updateLoginYN(HashMap<String, Object> paramMap) {
    	return boardDAO.updateLoginYN(paramMap);
    }
    
    
    // 계정정보
    public HashMap<String, Object> selectUserInfoOne(HashMap<String, Object> paramMap) {
    	return boardDAO.selectUserInfoOne(paramMap);
    }
    
    // 계정수정
    public HashMap<String, Object> updateUserInfo(HashMap<String, Object> paramMap) {
    	return boardDAO.updateUserInfo(paramMap);
    }
    

}
