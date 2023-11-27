package student;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCTest {

	public static void main(String[] args) {
		Connection conn;
		final String JDBC_DRIVER = "org.h2.Driver";
		final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "jwbook", "1234");
			System.out.println("연결 성공: " + conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
