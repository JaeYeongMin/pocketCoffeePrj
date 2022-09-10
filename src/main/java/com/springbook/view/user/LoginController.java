package com.springbook.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;


@Controller
public class LoginController {

    @RequestMapping(value = "/login.do" , method=RequestMethod.GET)
    public String loginView(UserVO vo) {
        System.out.println("");
        System.out.println("[LoginCtr]:: loginView() 로그인 화면으로 이동 ");
        
        vo.setId("test");
        vo.setPassword("test123");
        
        return "login.jsp";

    }
    
    
    @RequestMapping(value = "/login.do" , method=RequestMethod.POST)
    public String login(UserVO vo, UserDAO userDAO, HttpSession session) {
        
        if(vo.getId()== null || vo.getId().equals("")) {
            throw new IllegalArgumentException("아이디는 반드시 입력해야 합니다.");
        }
        
        UserVO user = userDAO.getUser(vo);
        
        
        System.out.println(user.getName());
        if(userDAO.getUser(vo) != null){
            // 3. 화면 네비게이션
            session.setAttribute("userName" , user.getName());
            return "getBoardList.do";
        }else{
            return "login.jsp";
        }

    }

    

}
