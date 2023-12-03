package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;

public class MemberDAO {
	//필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//회원 가입
	public void addMember(Member member) {
		conn = JDBCUtil.getConnection();
		String sql = "INSERT INTO member(mno, id, passwd, name, email, gender) "
						+ "VALUES (seq_mno.NEXTVAL, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getGender());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	//회원 목록
	public List<Member> getMemberList(){
		List<Member> memberList = new ArrayList<>();
		conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM member ORDER BY mno";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member member = new Member();
				member.setMno(rs.getInt("mno"));
				member.setId(rs.getString("id")); //db에서 꺼내옴
				member.setPasswd(rs.getString("passwd"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setGender(rs.getString("gender"));
				member.setJoinDate(rs.getTimestamp("joindate"));
				
				memberList.add(member);  //리스트에 저장
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return memberList;
	}
	
	//회원 상세 보기(정보)
	public Member getMember(String id) {
		Member member = new Member();
		conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM member WHERE id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member.setMno(rs.getInt("mno"));
				member.setId(rs.getString("id")); //db에서 꺼내옴
				member.setPasswd(rs.getString("passwd"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setGender(rs.getString("gender"));
				member.setJoinDate(rs.getTimestamp("joindate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return member;
	}
	
	//로그인 체크
	public Member checkLogin(Member member) {
		conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM member WHERE "
				+ "id = ? and passwd = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return member;
	}
	
	//회원 삭제
	public void deleteMember(String id) {
		conn = JDBCUtil.getConnection();
		String sql = "DELETE FROM member WHERE id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();  //db에서 삭제
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	//회원 수정
	public void updateMember(Member member) {
		conn = JDBCUtil.getConnection();
		String sql = "UPDATE member SET passwd = ?, name = ?, email = ?, "
				+ "gender = ? WHERE id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPasswd());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getGender());
			pstmt.setString(5, member.getId());
			pstmt.executeUpdate();  //수정 처리
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	//id 중복 체크
	public boolean duplicatedID(String id) {
		boolean result = false;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT DECODE(count(*), 1, 'true', 'false') as result "
					+ "FROM member WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getBoolean("result");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return result;
	}
	
	//아이디로 세션 이름 가져오기
	/*public String getNameById(String id) {
		conn = JDBCUtil.getConnection();
		String name = null;
		String sql = "SELECT * From member WHERE id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				name = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return name;
	}*/
}
