package reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;

public class ReplyDAO {
	//필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//댓글 목록
	public List<Reply> getReplyList(int bno){
		List<Reply> replyList = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * FROM reply WHERE bno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Reply reply = new Reply();
				reply.setRno(rs.getInt("rno"));
				reply.setBno(rs.getInt("bno"));
				reply.setRcontent(rs.getString("rcontent"));
				reply.setReplyer(rs.getString("replyer"));
				reply.setRdate(rs.getTimestamp("rdate"));
				reply.setRupdate(rs.getTimestamp("rupdate"));
				
				replyList.add(reply);
			}	
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return replyList;
	}
	
	//댓글 등록
	public void insertReply(Reply reply) {
		try {
			conn = JDBCUtil.getConnection();
			String sql = "INSERT INTO reply (rno, bno, rcontent, replyer) "
					+ "VALUES (seq_rno.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reply.getBno());
			pstmt.setString(2, reply.getRcontent());
			pstmt.setString(3, reply.getReplyer());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	//댓글 삭제
	public void deleteReply(int rno) {
		try {
			conn = JDBCUtil.getConnection();
			String sql = "DELETE FROM reply WHERE rno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rno);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	//댓글 1개 보기
	public Reply getReply(int rno){
		Reply reply = new Reply();
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * FROM reply WHERE rno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rno);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				reply.setRno(rs.getInt("rno"));
				reply.setBno(rs.getInt("bno"));
				reply.setRcontent(rs.getString("rcontent"));
				reply.setReplyer(rs.getString("replyer"));
				reply.setRdate(rs.getTimestamp("rdate"));
				reply.setRupdate(rs.getTimestamp("rupdate"));
			}	
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return reply;
	}
	
	//댓글 수정
	public void updateReply(Reply reply) {
		try {
			conn = JDBCUtil.getConnection();
			String sql = "UPDATE reply SET rcontent = ?, rupdate = ? WHERE rno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply.getRcontent());
			pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			pstmt.setInt(3, reply.getRno());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
}
