package com.springbook.biz.board.control;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.service.BoardService;
import com.springbook.zout.ArduinoMotorControl;



@Controller
@SessionAttributes("Board")  // 모델 속성을 세션에도 저장
public class BoardController{
    
    @Autowired
    private BoardService boardService;
    
    // 게시판 리스트
    @RequestMapping("/board/getBoardList.do")
    public ModelAndView getBoardList(ModelAndView mav, HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap) throws Exception {
    	mav.addObject("boardList" , boardService.getBoardList(paramMap));
    	return mav;
    }
    
    
}