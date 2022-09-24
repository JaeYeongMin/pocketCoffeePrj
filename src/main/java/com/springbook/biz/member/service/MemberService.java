package com.springbook.biz.member.service;

import java.util.HashMap;
import java.util.List;

public interface MemberService {

	// 회원등록
	String createMember(HashMap<String,Object> paramMap);
	
	// 로그인
	HashMap<String, Object> loginMember(HashMap<String,Object> paramMap);
    
    public HashMap<String,Object> getMemberDetail(HashMap<String,Object> paramMap);
    

}