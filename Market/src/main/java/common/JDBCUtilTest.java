package common;

import java.sql.Connection;

public class JDBCUtilTest {

	public static void main(String[] args) {
		Connection conn = JDBCUtil.getConnection();
		System.out.println("연결 객체 생성: " + conn);
	}

}
