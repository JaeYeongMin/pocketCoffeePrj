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
        return  mybatis.selectList("OrderSQL.getPrdList",paramMap);
    }
    


    // 주문등록
    public HashMap<String,Object> createOrder(HashMap<String,Object> paramMap) {
    	String orderSeq = null;
    	HashMap<String, Object> resultMap = new HashMap<String, Object>();
    	
    	
    	HashMap<String, Object> ordMstMap = new HashMap<String, Object>();
    	HashMap<String, Object> ordDtMap = new HashMap<String, Object>();
    	
    	try {
    		
    		
    		
    		// 오늘의 날짜와 차수를 조회한다.
    		HashMap<String, Object> roundInfo = mybatis.selectOne("OrderSQL.getOrderRound",paramMap);
    		

            paramMap.put("ORDER_ROUND", roundInfo.get("ORDER_ROUND"));
            paramMap.put("ORDER_DATE", roundInfo.get("ORDER_DATE"));

    		// ***** 주문마스터 등록 *****
        	mybatis.insert("OrderSQL.createOrderMaster",paramMap);
        	orderSeq = paramMap.get("ORDER_SEQ").toString();
    		
        	ArrayList<HashMap<String, Object>> orderList = (ArrayList<HashMap<String, Object>>) paramMap.get("ORDER_LIST");
        	
        	
        	
        	// 주문한 수 만큼 등록을 한다.
        	for(int i=0; i<orderList.size(); i++) {
        		
        		HashMap<String, Object> oInfo = orderList.get(i);
        		
        		
        		oInfo.put("ORDER_SEQ", orderSeq);
        		oInfo.put("MEMBER_SEQ", paramMap.get("MEMBER_SEQ"));

        		// ***** 주문상세 등록 *****
            	mybatis.insert("OrderSQL.createOrderDetail", oInfo);
        	}
    		
        	// 주문조회
        	HashMap<String, Object> orderMap = mybatis.selectOne("OrderSQL.getOrderMasterList", paramMap);
        	
        	// 상품조회
        	List<HashMap<String, Object>> detailList = mybatis.selectList("OrderSQL.getOrderDetailList", paramMap);
        	
        	
        	resultMap.put("ORDER_SEQ", orderMap.get("ORDER_SEQ"));
        	resultMap.put("ORDER_ROUND", orderMap.get("ORDER_ROUND"));
        	resultMap.put("ORDER_DATE", orderMap.get("ORDER_DATE"));
        	resultMap.put("MEMBER_NICK", orderMap.get("MEMBER_NICK"));
        	resultMap.put("ORDER_LIST", detailList);
        	
        	
    		
    	}catch(Exception e) {
    		resultMap.put("RETN_MENT", "시스템오류");
    		resultMap.put("RETN_CODE", "999");
    		System.out.println(e.getMessage());
    		
    	}
    	


        return resultMap;
    }

}
