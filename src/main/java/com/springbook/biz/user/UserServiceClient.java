package com.springbook.biz.user;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserServiceClient {

    public static void main(String[] args) {
        
        // 1. 스프링컨테이너를 구동한다.
        AbstractApplicationContext container = 
                new GenericXmlApplicationContext("applicationContext.xml");
        
        // 2. Spring 컨테이너로부터 UserServiceImpl 객체를 LookUp 한다.
        UserService userService = (UserService) container.getBean("userService");
        
        
        // 3. 로그인 기능테스트
        UserVO ainfo = new UserVO();
        
        ainfo.setId("test");
        ainfo.setPassword("test123");
        
        
        UserVO user = userService.getUser(ainfo);
        
        if(user != null) {
            System.out.println(user.getName() + "님 환영합니다.");
        }else {
            System.out.println("로그인 실패");
        }
        
        
        
        
        
        
        // 4. 컨에티어 종료
        container.close();

    }

}
