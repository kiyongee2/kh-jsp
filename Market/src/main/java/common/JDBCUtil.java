package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {
	//필드
	static String driverClass = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/jwebdb?serverTime=Asia/Seoul";
	static String user = "javauser";
	static String password = "pwjavauser";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	//db 연결 메서드
	public static Connection getConnection(){
		try {
			Class.forName(driverClass);
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//db 연결 종료(삽입, 수정, 삭제)
	public static void close(Connection conn, 
			PreparedStatement pstmt) {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//db 연결 종료 메서드(조회)
	public static void close(Connection conn, 
			PreparedStatement pstmt, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
