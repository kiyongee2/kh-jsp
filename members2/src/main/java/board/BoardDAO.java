package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;
import reply.Reply;

public class BoardDAO {
	//필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//게시글 목록
	public List<Board> getBoardList(){
		List<Board> boardList = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * FROM board ORDER BY bno DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setBno(rs.getInt("bno"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setCreateDate(rs.getTimestamp("createdate"));
				board.setModifyDate(rs.getTimestamp("modifydate"));
				board.setHit(rs.getInt("hit"));
				board.setFilename(rs.getString("filename"));
				board.setId(rs.getString("id"));
				
				boardList.add(board);  //개별 board 객체를 추가 저장
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return boardList;
	}
	
	//게시글 목록(페이지 처리)
	public ArrayList<Board> getBoardList(int page){
		ArrayList<Board> boardList = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * "
					+ "FROM (SELECT ROWNUM rn, boards.* "
					+ "            FROM(SELECT * FROM board ORDER BY bno DESC) boards) "
					+ "WHERE rn >= ? AND rn <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (page-1)*10 + 1);  //시작행
			pstmt.setInt(2, page*10);  //페이지x페이지당 게시글 수
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setBno(rs.getInt("bno"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setCreateDate(rs.getTimestamp("createdate"));
				board.setModifyDate(rs.getTimestamp("modifydate"));
				board.setHit(rs.getInt("hit"));
				board.setId(rs.getString("id"));
				
				boardList.add(board);  //개별 board 객체를 추가 저장
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return boardList;
	}
	
	//게시글 목록(검색 처리)
	public ArrayList<Board> getBoardList(String field, String kw){
		ArrayList<Board> boardList = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * FROM board WHERE " + field + " LIKE ? ORDER BY bno";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+kw+"%");  //검색어가 포함된 단어
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setBno(rs.getInt("bno"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setCreateDate(rs.getTimestamp("createdate"));
				board.setModifyDate(rs.getTimestamp("modifydate"));
				board.setHit(rs.getInt("hit"));
				board.setId(rs.getString("id"));
				
				boardList.add(board);  //개별 board 객체를 추가 저장
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return boardList;
	}
	
	//게시글 목록(검색 및 페이지 처리)
	public ArrayList<Board> getBoardList(String field, String kw, int page){
		ArrayList<Board> boardList = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * "
					+ "FROM (SELECT ROWNUM rn, bo.* "
					+ "      FROM(SELECT * FROM board WHERE "
					+ 			field + " LIKE ? ORDER BY bno DESC) bo) "
					+ "WHERE rn BETWEEN ? AND ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+kw+"%");
			pstmt.setInt(2, (page-1)*10 + 1);  //시작행
			pstmt.setInt(3, page*10);  //페이지x페이지당 게시글 수
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setBno(rs.getInt("bno"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setCreateDate(rs.getTimestamp("createdate"));
				board.setModifyDate(rs.getTimestamp("modifydate"));
				board.setHit(rs.getInt("hit"));
				board.setId(rs.getString("id"));
				board.setReplycnt(rs.getInt("replycnt"));
				board.setVoter(rs.getInt("voter"));
				
				boardList.add(board);  //개별 board 객체를 추가 저장
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return boardList;
	}
	
	//게시글 총 개수
	public int getBoardCount() {
		int total = 0;
		conn = JDBCUtil.getConnection();
		try {
			String sql = "SELECT COUNT(*) AS total FROM board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next())
				total = rs.getInt("total");  //db에서 총개수 반환
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return total;
	}
	
	//게시글 쓰기
	public void addBoard(Board board) {
		conn = JDBCUtil.getConnection();
		String sql = "INSERT INTO board(bno, title, content, filename, id) "
				+ "VALUES (seq_bno.NEXTVAL, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getFilename());
			pstmt.setString(4, board.getId());
			pstmt.executeUpdate();  //db에 저장
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	//게시글 상세 보기
	public Board getBoard(int bno) {
		Board board = new Board();
		conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM board WHERE bno = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);  //글 번호 바인딩 시킴
			rs = pstmt.executeQuery(); //검색한 자료 가져옴
			if(rs.next()) { //데이터가 있으면 db에서 칼럼을 가져옴
				board.setBno(rs.getInt("bno"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setCreateDate(rs.getTimestamp("createdate"));
				board.setModifyDate(rs.getTimestamp("modifydate"));
				board.setHit(rs.getInt("hit"));
				board.setFilename(rs.getString("filename"));
				board.setId(rs.getString("id"));
				board.setVoter(rs.getInt("voter"));
				
				//조회수 1증가(수정이 발생함)
				int hit = rs.getInt("hit") + 1;
				
				sql = "UPDATE board SET hit = ? WHERE bno = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, hit);
				pstmt.setInt(2, bno);
				pstmt.executeUpdate();  //다시 db에 저장
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return board;
	}
	
	//게시글 삭제
	public void deleteBoard(int bno) {
		conn = JDBCUtil.getConnection();
		String sql = "DELETE FROM board WHERE bno = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();  //db에서 삭제
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	//게시글 수정
	public void updateBoard(Board board) {
		conn = JDBCUtil.getConnection();
		//현재 시간 객체 생성
		Timestamp now = new Timestamp(System.currentTimeMillis());
		String sql = "UPDATE board SET title=?, content=?, "
				+ "modifydate=? WHERE bno = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setTimestamp(3, now);
			pstmt.setInt(4, board.getBno());
			pstmt.executeUpdate();  //db에 저장
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	//댓글 수 조회
	public void updateReplycnt(int bno) {
		try {
			conn = JDBCUtil.getConnection();
			String sql = "UPDATE board "
					+ "SET replycnt = ( "
					+ "            SELECT count(rno) "
					+ "            FROM reply "
					+ "            WHERE bno = ?) "
					+ "WHERE bno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setInt(2, bno);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	//추천수(좋아요)
	public void updateVoter(int bno) {
		try {
			conn = JDBCUtil.getConnection();
			String sql = "UPDATE board SET voter = voter + 1 "
					+ "WHERE bno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
}





