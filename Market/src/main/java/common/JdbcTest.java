package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTest {

	public static void main(String[] args) {
		//필드
		String driverClass = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/jwebdb?serverTime=Asia/Seoul";
		String user = "javauser";
		String password = "pwjavauser";
		Connection conn = null;
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("연결 성공: " + conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
