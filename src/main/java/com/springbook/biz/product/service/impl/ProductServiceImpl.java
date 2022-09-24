package com.springbook.biz.product.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.product.service.ProductService;


@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    // 회원 상세조회
    public List<HashMap<String,Object>> getPrdList(HashMap<String,Object> paramMap) {
        return productDAO.getPrdList(paramMap);
    }
    



}
