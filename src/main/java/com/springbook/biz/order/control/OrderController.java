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


import com.springbook.biz.order.service.OrderService;
import com.springbook.view.common.StringUtil;



@Controller
public class OrderController{
    
    @Autowired
    private OrderService orderService;
    

    // 주문등록
    @RequestMapping("/order/createOrder.do")
    public @ResponseBody HashMap<String, Object> createOrder(@RequestBody String inputJSON, ModelAndView mav, HttpServletRequest request) throws Exception {
    	HashMap<String, Object> resMap = new HashMap<String, Object>();
    	HashMap<String, Object> resHead = new HashMap<String, Object>();
    	HashMap<String, Object> ainfo = new HashMap<String, Object>();
    	List<HashMap<String, Object>> prdList = new ArrayList<HashMap<String, Object>>();
    	HashMap<String, Object> resBody = new HashMap<String, Object>();
		// JSON을 HashMap으로 변환해준다.
    	HashMap<String,Object> paramMap = (HashMap<String,Object>) new ObjectMapper().readValue(inputJSON, Map.class);
    	HashMap<String, Object> reqMap = (HashMap<String, Object>) paramMap.get("REQ_BODY");
		
		String retnMent = null;
		String retnCode = null;
		try {
			
			String result = orderService.createOrder(reqMap);

			if(prdList.size() < 1 || prdList == null) {
				ainfo.put("PRD_CATE_SUB", null);
				ainfo.put("PRD_NAME_KR", null);
				ainfo.put("INPUT_ID", null);
				ainfo.put("PRD_CNT", null);
				ainfo.put("PRD_SEQ", null);
				ainfo.put("PRD_PRICE", null);
				ainfo.put("DB_STATUS", null);
				ainfo.put("PRD_NAME_EN", null);
				ainfo.put("PRD_CATE", null);
				ainfo.put("PRD_NOTICE", null);
				ainfo.put("PRD_IMG", null);
				ainfo.put("PRD_TYPE", null);
				
				prdList.add(ainfo);
				resBody.put("PRD_LIST", prdList);
				retnMent = "상품리스트가없습니다.";
				retnCode = "200";
			}else {
				resBody.put("PRD_LIST", prdList);
				retnMent = "성공";
				retnCode = "200";
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