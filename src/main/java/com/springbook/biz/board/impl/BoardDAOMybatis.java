package com.springbook.biz.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;


// Board DAO
@Repository("boardDAO")     //context 설정파일에 등록한다.
public class BoardDAOMybatis{
	@Autowired
	private SqlSessionTemplate mybatis;
    


    // CRUD 기능의 메소드 구현
    // 글등록
    public void insertBoard(BoardVO vo) {
        mybatis.insert("BoardDAO.insertBoard",vo);
         
    }

    
    
    // 글수정
    public void updateBoard(BoardVO vo) {
        mybatis.update("BoardDAO.updateBoard",vo);
        
    }
    
    
    // 글 삭제
    public void deleteBoard(BoardVO vo) {
        mybatis.delete("BoardDAO.deleteBoard",vo);
        
    }
    
    
    
    // 글 상세조회
    public BoardVO getBoard(BoardVO vo) {
        return (BoardVO) mybatis.selectOne("BoardDAO.getBoard",vo);
    }
    

    // 글 목록조회
    public List<BoardVO> getBoardList(BoardVO vo) {
        return mybatis.selectList("BoardDAO.getBoardList",vo);
    }
    
    

}
