package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;

// import com.springbook.biz.user.UserVO;

public class  AfterReturningAdvice {
	/*    
    public void afterLog(JoinPoint jp, UserVO returnObj) {
        
        String method  = jp.getSignature().getName();
        
        if(returnObj.getRole().equals("ADMIN")){
            System.out.println(returnObj.getName()+"로그인(admin)");
        }
        
    
        if(returnObj instanceof UserVO){
            UserVO user = (UserVO)returnObj;
            if(user.getRole().equals("ADMIN")){
                System.out.println(user.getName()+"로그인(Admin)");
            }
        }
        
        System.out.println("[사후 처리] "+method+"() 메소드 리턴값:" + returnObj.toString());

        
    }
    */
}
