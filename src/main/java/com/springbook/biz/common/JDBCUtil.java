package com.springbook.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class JDBCUtil {
    
    public static Connection getConnection() {
        try {
            // Class.forName("com.mysql.cj.jdbc.Driver");
            // return DriverManager.getConnection("jdbc:mysql://localhost:3306/mjydbzz?serverTimezone=UTC","mjydbzz","tkadms4379!");
        	Class.forName("org.mariadb.jdbc.Driver");
        	return DriverManager.getConnection("jdbc:mariadb://sameun12.cafe12.com:3306/sameun12","sameun12","Tkadms4379@");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public static void close(PreparedStatement stmt, Connection conn) {
        if(stmt!=null) {
            try {
                if(!stmt.isClosed()) stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                stmt = null;
            }
        }
    }
    
    
    public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
        if(rs!=null) {
            try {
                if(!rs.isClosed()) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                rs=null;
            }
        }
        
        if(stmt != null) {
            try {
                if(!stmt.isClosed()) stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                stmt = null;
            }
        }
        
        
        if(conn != null) {
            try {
                if(!conn.isClosed()) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conn = null;
            }
        }
        
        
    }
}
