package com.springbook.biz.member;

import java.util.HashMap;
import java.util.List;

public interface MemberService {

	// 회원등록
	String createMember(HashMap<String,Object> paramMap);
	
	// 로그인
	HashMap<String, Object> loginMember(HashMap<String,Object> paramMap);

    void updateMember(MemberVO vo);


    void deleteMember(MemberVO vo);

    MemberVO getMember(MemberVO vo);
    
    
    public HashMap<String,Object> getMemberDetail(HashMap<String,Object> paramMap);
    
    // 글 목록조회
    List<MemberVO> getMemberList(MemberVO vo);

}