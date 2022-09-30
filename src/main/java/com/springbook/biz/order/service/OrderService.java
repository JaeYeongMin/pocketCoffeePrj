package com.springbook.biz.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface OrderService {


    
    public List<HashMap<String,Object>> getOrderList(HashMap<String,Object> paramMap);
	
    
    // 주문등록
    HashMap<String,Object> createOrder(HashMap<String,Object> paramMap);
	

}