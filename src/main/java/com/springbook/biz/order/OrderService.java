package com.springbook.biz.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface OrderService {


    
    public List<HashMap<String,Object>> getPrdList(HashMap<String,Object> paramMap);
	
    
    // 주문등록
	String createOrder(HashMap<String,Object> paramMap);
	

}