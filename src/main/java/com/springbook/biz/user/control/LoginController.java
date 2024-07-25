package com.springbook.biz.user.control;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.user.service.UserService;



@Controller
@SessionAttributes("member")  // 모델 속성을 세션에도 저장
public class LoginController{
    
    @Autowired
    private UserService userService;
    
    
    
    @ModelAttribute("conditionMap")
    public Map<String,String> serchConditionMap(){
        Map<String,String> conditionMap = new HashMap<String,String>();
        conditionMap.put("제목", "TITLE");
        conditionMap.put("내용", "CONTENT");
      
        return conditionMap;
    }
    


    
    

    // 로그인
    @RequestMapping("/user/login.do")
    public void login(ModelAndView mav, HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap) throws Exception {
    	HashMap<String, Object> resultMap = userService.updateLoginYN(paramMap);
    }
    
    

    // 계정상세
    @RequestMapping("/user/userDetail.do")
    public ModelAndView userDetail(ModelAndView mav, HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap) throws Exception {
    	HashMap<String, Object> resultMap = userService.selectUserInfoOne(paramMap);
    	mav.addObject("detail", resultMap);
    	return mav;
    	
    }
    
    
    // 계정수정
    @RequestMapping("/user/updateUserInfo.do")
    public @ResponseBody HashMap<String, Object> updateUserInfo(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap) throws Exception {
    	userService.updateUserInfo(paramMap);
    	HashMap<String, Object> resultMap = userService.selectUserInfoOne(paramMap);
    	return resultMap;
    	
    }
    
    // 계정체크
    @RequestMapping("/user/chkUserInfo.do")
    public @ResponseBody HashMap<String, Object> chkUserInfo(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap) throws Exception {
    	HashMap<String, Object> resultMap = userService.selectUserInfoOne(paramMap);
    	return resultMap;
    	
    }
    

}