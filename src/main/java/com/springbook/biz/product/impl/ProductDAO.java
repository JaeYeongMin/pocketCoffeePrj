package com.springbook.biz.product.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



// Product DAO
@Repository("productDAO")     //context 설정파일에 등록한다.
public class ProductDAO{
	@Autowired
	private SqlSessionTemplate mybatis;


    // 회원 상세 조회
    public List<HashMap<String, Object>> getPrdList(HashMap<String, Object> paramMap) {
        return  mybatis.selectList("ProductDAO.getPrdList",paramMap);
    }
    
    

}
