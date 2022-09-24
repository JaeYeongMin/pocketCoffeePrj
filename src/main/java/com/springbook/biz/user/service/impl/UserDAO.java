/*package com.springbook.biz.user.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;


import com.springbook.biz.user.UserVO;
import com.springbook.biz.common.JDBCUtil;


// User DAO
@Repository("userDAO")     //context 설정파일에 등록한다.
public class UserDAO {
    
    // JDBC 관련변수
    private Connection conn        = null;
    private PreparedStatement stmt = null;
    private ResultSet rs           = null;
    
    
    // SQL 명령어
    private final String USER_GET  = "SELECT * FROM TB_MEMBER WHERE MEMBER_ID=? AND MEMBER_PW=?";
    
    
    
    // CRUD 기능의 메소드 구현    
    
    // 회원등록
    public UserVO getUser(UserVO vo) {
        System.out.println("===> JDBC로 GetUser() 기능처리");
        UserVO user = null;
        try {
            conn =JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USER_GET);
            stmt.setString(1, vo.getId());
            stmt.setString(2, vo.getPassword());
            
            rs = stmt.executeQuery();
            
            if(rs.next()) {
                user = new UserVO();
                user.setId(rs.getString("MEMBER_ID"));
                user.setPassword(rs.getString("MEMBER_PW"));
                user.setName(rs.getString("MEMBER_NAME"));
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(rs,stmt, conn);
        }
        return user;
    }
 
    

}
*/