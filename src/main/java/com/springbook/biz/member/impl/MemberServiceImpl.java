package com.springbook.biz.member.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.member.MemberService;
import com.springbook.biz.member.MemberVO;


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
    
    public void updateMember(MemberVO vo) {
        memberDAO.updateMember(vo);
    }
    
    public void deleteMember(MemberVO vo) {
        memberDAO.deleteMember(vo);
    }
    
    public MemberVO getMember(MemberVO vo) {
        return memberDAO.getMember(vo);
    }
    
    // 회원 상세조회
    public HashMap<String,Object> getMemberDetail(HashMap<String,Object> paramMap) {
        return memberDAO.getMemberDetail(paramMap);
    }
    
    public List<MemberVO> getMemberList(MemberVO vo){
        return memberDAO.getMemberList(vo);
    }


}
