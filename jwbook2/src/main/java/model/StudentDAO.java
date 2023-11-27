package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;

public class StudentDAO {
	//필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
		
	//회원 목록
	public List<Student> getStudentList(){
		conn = JDBCUtil.getConnection();
		
		List<Student> students = new ArrayList<>();
		try {
			String sql = "SELECT * FROM student ORDER BY sid";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Student s = new Student();
				s.setSid(rs.getInt("sid"));
				s.setUsername(rs.getString("username"));
				s.setUniv(rs.getString("univ"));
				s.setBirth(rs.getString("birth"));
				s.setEmail(rs.getString("email"));
				
				students.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return students;
	}
	
	//학생 추가
	public void insertStudent(Student s) {
		conn = JDBCUtil.getConnection();
		
		try {
			String sql = "INSERT INTO student (sid, username, univ, birth, email) "
					+ "VALUES (seq_sid.NEXTVAL, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s.getUsername());
			pstmt.setString(2, s.getUniv());
			pstmt.setString(3, s.getBirth());
			pstmt.setString(4, s.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
}


