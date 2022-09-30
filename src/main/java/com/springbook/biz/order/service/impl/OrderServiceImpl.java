package com.springbook.biz.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.order.service.OrderService;


@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDAO orderDAO;

    // 주문조회
    public List<HashMap<String,Object>> getOrderList(HashMap<String,Object> paramMap) {
        return orderDAO.getOrderList(paramMap);
    }
    


    // 주문등록
    public HashMap<String,Object> createOrder(HashMap<String,Object> paramMap) {
    	return orderDAO.createOrder(paramMap);
    }

}
