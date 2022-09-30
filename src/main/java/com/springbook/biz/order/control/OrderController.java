package com.springbook.biz.order.control;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.member.service.MemberService;
import com.springbook.biz.order.service.OrderService;
import com.springbook.view.common.StringUtil;



@Controller
public class OrderController{
    
    @Autowired
    private OrderService orderService;
    
    
    @Autowired
    private MemberService memberService;
    
/*

{
  "REQ_BODY" : {
       "MEMBER_SEQ" : 1
      ,"ORD_SECT" : "IN"
      ,"ORDER_LIST" :[
          { 
              "PRD_SEQ" : 1
            , "ORD_CNT" : 2
            , "ORD_SHOT" : 2
            , "ORD_TYPE" : "ICE"
            , "ORD_SIZE" : "GRANDE"
          }, {
              "PRD_SEQ" : 2
            , "ORD_CNT" : 1
            , "ORD_SHOT" : 2
            , "ORD_TYPE" : "ICE"
            , "ORD_SIZE" : "GRANDE"
          }
      ]
  }
}

  
  
*/  

    // 주문등록
    @RequestMapping("/order/createOrder.do")
    public @ResponseBody HashMap<String, Object> createOrder(@RequestBody String inputJSON, ModelAndView mav, HttpServletRequest request) throws Exception {
    	HashMap<String, Object> resMap = new HashMap<String, Object>();
    	HashMap<String, Object> resHead = new HashMap<String, Object>();
    	HashMap<String, Object> resBody = new HashMap<String, Object>();
    	HashMap<String, Object> resultMap =  new HashMap<String, Object>();
		// JSON을 HashMap으로 변환해준다.
    	HashMap<String,Object> paramMap = (HashMap<String,Object>) new ObjectMapper().readValue(inputJSON, Map.class);
    	HashMap<String, Object> reqMap = (HashMap<String, Object>) paramMap.get("REQ_BODY");
		
		String retnMent = null;
		String retnCode = null;
		try {
			
			ArrayList<HashMap<String,Object>> orderList = (ArrayList<HashMap<String, Object>>) reqMap.get("ORDER_LIST");
			
			
			if(orderList.size() > 0) {

				resBody = orderService.createOrder(reqMap);
				retnMent = "성공";
				retnCode = "200";
			}else {
				retnMent = "주문메뉴가 없습니다.";
				retnCode = "999";
			}
			

		} catch (Exception e){
			retnMent = "시스템 오류 입니다.";
			retnCode = "999";
			resBody = null;
		} finally {
			resHead.put("RETN_CODE", retnCode);
			resHead.put("RETN_MENT", retnMent);
		}
		
		resMap.put("RES_BODY", resBody);
		resMap.put("RES_HEAD", resHead);
		
        return resMap;  // View 이름 리턴
    }
    
    
    


    // 상품리스트조회
    @RequestMapping("/order/getOrderList.do")
    public @ResponseBody HashMap<String, Object> getOrderList(@RequestBody String inputJSON, ModelAndView mav, HttpServletRequest request) throws Exception {
    	HashMap<String, Object> resMap = new HashMap<String, Object>();
    	HashMap<String, Object> memberMap = new HashMap<String, Object>();
    	HashMap<String, Object> resHead = new HashMap<String, Object>();
    	List<HashMap<String, Object>> ordList = new ArrayList<HashMap<String, Object>>();
    	HashMap<String, Object> resBody = new HashMap<String, Object>();
		// JSON을 HashMap으로 변환해준다.
    	HashMap<String,Object> paramMap = (HashMap<String,Object>) new ObjectMapper().readValue(inputJSON, Map.class);
    	HashMap<String, Object> reqMap = (HashMap<String, Object>) paramMap.get("REQ_BODY");
		
		String retnMent = null;
		String retnCode = null;
		try {
			
			if(StringUtil.getEmptyString(reqMap.get("MEMBER_SEQ")).equals("")) {
				retnMent = "필수값 누락";
				retnCode = "400";
			}else {

				
				ordList = orderService.getOrderList(reqMap);
				memberMap = memberService.getMemberDetail(reqMap);
				
				if(ordList.size() < 1 || ordList == null) {

					resBody.put("MEMBER_SEQ", null);
					resBody.put("ORDER_LIST", null);
					retnMent = "주문리스트가 없습니다.";
					retnCode = "200";
				}else {
					resBody.put("ORDER_LIST", ordList);
					resBody.put("MEMBER_NICK", StringUtil.getEmptyString(memberMap.get("MEMBER_NICK")));
					
					retnMent = "성공";
					retnCode = "200";
					
				}
				
			}


		} catch (Exception e){
			retnMent = "시스템 오류 입니다.";
			retnCode = "999";
			resBody = null;
		} finally {
			
			resHead.put("RETN_CODE", retnCode);
			resHead.put("RETN_MENT", retnMent);
		}
		
		resMap.put("RES_BODY", resBody);
		resMap.put("RES_HEAD", resHead);
		
        return resMap;  // View 이름 리턴
    }
    
    
    
}