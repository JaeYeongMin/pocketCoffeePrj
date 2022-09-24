package com.springbook.biz.member.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.member.service.MemberService;


@Service("memberService")
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDAO memberDAO;


    // 회원등록 
    public String createMember(HashMap<String,Object> paramMap) {
        return memberDAO.createMember(paramMap);  
    }
    
    // 로그인 
    public HashMap<String, Object> loginMember(HashMap<String,Object> paramMap) {
        return memberDAO.loginMember(paramMap);
    }

    
    // 회원 상세조회
    public HashMap<String,Object> getMemberDetail(HashMap<String,Object> paramMap) {
        return memberDAO.getMemberDetail(paramMap);
    }



}
