package com.springbook.view.user;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LogoutController{

    @RequestMapping("/logout.do")
    public String logout(HttpSession session ) {
        System.out.println("");
        System.out.println("[LogoutCtr]:: logout() CAll_SUCC ");

        session.invalidate();
        return "login.jsp";
    }

}
