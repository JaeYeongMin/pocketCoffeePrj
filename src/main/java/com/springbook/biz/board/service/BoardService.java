package com.springbook.biz.board.service;

import java.util.HashMap;

public interface BoardService {

    // 로그인 상태 업데이트 
	public HashMap<String,Object> updateLoginYN(HashMap<String,Object> paramMap);
	
	
    // 계정정보 검색 
	public HashMap<String,Object> selectUserInfoOne(HashMap<String,Object> paramMap);
	
	
    // 계정정보 수정
	public HashMap<String,Object> updateUserInfo(HashMap<String,Object> paramMap);
	
}