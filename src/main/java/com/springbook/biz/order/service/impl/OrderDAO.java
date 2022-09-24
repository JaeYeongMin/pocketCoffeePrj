package com.springbook.biz.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



// Order DAO
@Repository("orderDAO")     //context 설정파일에 등록한다.
public class OrderDAO{
	@Autowired
	private SqlSessionTemplate mybatis;


    // 회원 상세 조회
    public List<HashMap<String, Object>> getPrdList(HashMap<String, Object> paramMap) {
        return  mybatis.selectList("OrderDAO.getPrdList",paramMap);
    }
    


    // 주문등록
    public String createOrder(HashMap<String,Object> paramMap) {
    	String member_seq = null;
    	mybatis.insert("OrderDAO.createOrder",paramMap);
    	member_seq = paramMap.get("MEMBER_SEQ").toString();
        return member_seq;
         
    }

}
