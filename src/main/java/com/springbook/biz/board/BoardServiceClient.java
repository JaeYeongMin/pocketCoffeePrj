package com.springbook.biz.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class BoardServiceClient {

    public static void main(String[] args) {
        // 1.Spring 컨테이너를 구동한다. (스프링 빈 객체들 생성)
        AbstractApplicationContext container =
                new GenericXmlApplicationContext("applicationContext.xml");
        
        // 2. Spring 컨테이너로부터 BoardServiceImpl 객체를 LookUp한다.
        BoardService boardService = (BoardService) container.getBean("boardService");
        
        
        // 3. 글 등록 기능 테스트
        BoardVO vo = new BoardVO();
        vo.setTitle("휴가계획서");
        vo.setContent("드디어 나에게 맞는 회사를 찾았다.");
        vo.setWriter("MJYDB");
        
        boardService.insertBoard(vo);
        
        
        // 4. 글 목록 검색 기능 테스트
        List<BoardVO> alist = boardService.getBoardList(vo);
        
        for(BoardVO ainfo : alist) {
            System.out.println("---->" + ainfo.toString());
        }
        

        // 5. Spring 컨테이너 종료
        container.close();
        
        
        
    }

}
