package com.springbook.biz.user.control;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.user.service.UserService;
import com.springbook.view.common.StringUtil;
import com.springbook.zout.ArduinoMotorControl;



@Controller
@SessionAttributes("member")  // 모델 속성을 세션에도 저장
public class LoginController{
    
    @Autowired
    private UserService userService;
    
    // 로그인
    @RequestMapping("/user/login.do")
    public void login(ModelAndView mav, HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap) throws Exception {

    }
    
    
    // 로그인 시도
    @RequestMapping("/user/Dologin.do")
    public ModelAndView Dologin(ModelAndView mav, HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap) throws Exception {
    	HashMap<String, Object> resultMap = userService.updateLoginYN(paramMap);
    	mav.setViewName("/user/login");
    	return mav;
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
    
    
    // 결과화면
    @RequestMapping("/user/boxDetail.do")
    public ModelAndView boxDetail(ModelAndView mav, HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap) throws Exception {
    	HashMap<String, Object> resultMap = userService.selectUserInfoOne(paramMap);
    	mav.addObject("detail", resultMap);
    	return mav;
    	
    }
    
    
    
    
    
    // 아두이노 통신
    @RequestMapping("/user/executeArduino.do")
    public @ResponseBody HashMap<String, Object> executeArduino(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap) throws Exception {
    	HashMap<String, Object> resultMap = userService.selectUserInfoOne(paramMap);

    	// 로그인이 되었다면 아두이노 시리얼통신 시작
    	if(StringUtil.getEmptyString(resultMap.get("LOGIN_YN")).equals("Y")) {
    		
    		ArduinoMotorControl motorControl = new ArduinoMotorControl();
            Thread.sleep(2100);
            
            // 모터를 전진(1)으로 동작시키기
            motorControl.sendCommand('1');
            Thread.sleep(3000); // 5초간 모터 동작
            // 모터를 정지(0)시키기
            motorControl.sendCommand('0');
            
            // 시리얼 포트 닫기
            motorControl.serialPort.close();
    		
    	}
    	
    	return resultMap;
    }
    

}