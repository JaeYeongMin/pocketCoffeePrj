package com.springbook.biz.board.service;

import java.util.HashMap;
import java.util.List;

public interface BoardService {
	
	
	
	
    // 게시판 리스트
	public List<HashMap<String,Object>> getBoardList(HashMap<String,Object> paramMap);

	
    // 게시판 수정
	public HashMap<String,Object> updateBoard(HashMap<String,Object> paramMap);
	
	
	
	
	
    // 로그인 상태 업데이트 
	public HashMap<String,Object> updateLoginYN(HashMap<String,Object> paramMap);
	
	
    // 계정정보 검색 
	public HashMap<String,Object> selectUserInfoOne(HashMap<String,Object> paramMap);
	
	
    // 계정정보 수정
	public HashMap<String,Object> updateUserInfo(HashMap<String,Object> paramMap);
	
}