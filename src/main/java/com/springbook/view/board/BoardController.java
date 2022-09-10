package com.springbook.view.board;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;



@Controller
@SessionAttributes("board")  // 모델 속성을 세션에도 저장
public class BoardController{
    
    @Autowired
    private BoardService boardService;
    
    
    
    @ModelAttribute("conditionMap")
    public Map<String,String> serchConditionMap(){
        Map<String,String> conditionMap = new HashMap<String,String>();
        conditionMap.put("제목", "TITLE");
        conditionMap.put("내용", "CONTENT");
      
        return conditionMap;
    }
    
    
    
    // 글 목록
    @RequestMapping("/getBoardList.do")
    public String getBoardList(BoardVO vo, Model model) {
        // NULL CHECK
        if(vo.getSearchCondition() == null) {
            vo.setSearchCondition("TITLE");
        }
        if(vo.getSearchKeyword() == null) {
            vo.setSearchKeyword("");
        }
        
        System.out.println("");
        System.out.println("조건: " + vo.getSearchCondition());
        System.out.println("내용: " + vo.getSearchKeyword());
        

        model.addAttribute("boardList" , boardService.getBoardList(vo));  // Model 정보 저장
        return "getBoardList.jsp";  // View 이름 리턴
    }
    
    // 글 상세
    @RequestMapping("/getBoard.do")
    public String getBoard(BoardVO vo, Model model) {
        System.out.println("");
        System.out.println("[BoardCtr]:: getBoardList() CAll_SUCC ");
        System.out.println("작성자 이름: " + vo.getWriter());

        model.addAttribute("board" , boardService.getBoard(vo));  // Model 정보 저장
        
        return "getBoard.jsp";  // View 이름 리턴
    }
    
    // 글 등록
    @RequestMapping("/insertBoard.do")
    public String insertBoard(BoardVO vo) throws IOException {
        System.out.println("");
        System.out.println("[BoardCtr]:: insertBoard() CAll_SUCC ");
        // 파일 업로드 처리
        MultipartFile uploadFile = vo.getUploadFile();
        if(!uploadFile.isEmpty()) {
            String fileName = uploadFile.getOriginalFilename();
            uploadFile.transferTo(new File("C:/Users/jaeyo/Desktop/GGGG/" + fileName));
        }
        
        boardService.insertBoard(vo);
        return "redirect:getBoardList.do";
    }
    
    // 글 수정
    @RequestMapping("/updateBoard.do")
    public String updateBoard(@ModelAttribute("board") BoardVO vo) {
        System.out.println("");
        System.out.println("[BoardCtr]:: updateBoard() CAll_SUCC ");
        System.out.println("번호    : " + vo.getSeq());
        System.out.println("제목    : " + vo.getTitle());
        System.out.println("작성자 : " + vo.getWriter());
        System.out.println("내용    : " + vo.getContent());
        System.out.println("등록일 : " + vo.getRegDate());
        System.out.println("조회수 : " + vo.getCnt());
        
        
        
        boardService.updateBoard(vo);
        return "getBoardList.do";
    }

    // 글 삭제
    @RequestMapping("/deleteBoard.do")
    public String deleteBoard(BoardVO vo) {
        System.out.println("");
        System.out.println("[BoardCtr]:: deleteBoard() CAll_SUCC ");
 
        boardService.deleteBoard(vo);
        return "getBoardList.do";
    }
}