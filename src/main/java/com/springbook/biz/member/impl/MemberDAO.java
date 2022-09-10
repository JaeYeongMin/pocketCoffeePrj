package com.springbook.biz.member.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.member.MemberVO;


// Member DAO
@Repository("memberDAO")     //context 설정파일에 등록한다.
public class MemberDAO{
	@Autowired
	private SqlSessionTemplate mybatis;
    


    // 회원등록
    public String createMember(HashMap<String,Object> paramMap) {
    	String member_seq = null;
    	mybatis.insert("MemberDAO.createMember",paramMap);
    	member_seq = paramMap.get("MEMBER_SEQ").toString();
        return member_seq;
         
    }
    
    // 로그인
    public HashMap<String, Object> loginMember(HashMap<String,Object> paramMap) {
        return mybatis.selectOne("MemberDAO.loginMember",paramMap);
         
    }

    
    
    public void updateMember(MemberVO vo) {
        mybatis.update("MemberDAO.updateMember",vo);
        
    }
    
    
    public void deleteMember(MemberVO vo) {
        mybatis.delete("MemberDAO.deleteMember",vo);
        
    }
    
    
    
    public MemberVO getMember(MemberVO vo) {
        return (MemberVO) mybatis.selectOne("MemberDAO.getMember",vo);
    }
    
    
    // 회원 상세 조회
    public HashMap<String, Object> getMemberDetail(HashMap<String, Object> paramMap) {
        return  (HashMap<String, Object>) mybatis.selectOne("MemberDAO.getMemberDetail",paramMap);
    }
    
    
    
    
    public List<MemberVO> getMemberList(MemberVO vo) {
        return mybatis.selectList("MemberDAO.getMemberList",vo);
    }
    
    

}
