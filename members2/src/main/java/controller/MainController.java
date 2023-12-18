package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.Board;
import board.BoardDAO;
import member.Member;
import member.MemberDAO;
import reply.Reply;
import reply.ReplyDAO;

@WebServlet("*.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberDAO memberDAO;
	BoardDAO boardDAO;
	ReplyDAO replyDAO;
       
    public MainController() {
        memberDAO = new MemberDAO();
        boardDAO = new BoardDAO();
        replyDAO = new ReplyDAO();
    }
	/*public void init(ServletConfig config) throws ServletException {
		memberDAO = new MemberDAO();  //객체 생성
	}*/

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 인코딩
		request.setCharacterEncoding("utf-8");
		
		//한글 컨텐츠 유형 응답
		response.setContentType("text/html; charset=utf-8");
		
		//command 패턴으로 url 설정하기
		String uri = request.getRequestURI();
		//System.out.println(uri);
		String command = uri.substring(uri.lastIndexOf('/'));
		//System.out.println(uri.lastIndexOf('/'));
		
		//이동할 페이지
		String nextPage = null;
		
		//세션 객체
		HttpSession session = request.getSession();
		
		//출력 스트림 객체 생성
		PrintWriter out = response.getWriter();
		
		if(command.equals("/main.do")) {
			//게시글 가져오기
			List<Board> boardList = boardDAO.getBoardList();
			//System.out.println("총게시글: " + boardList.size());
			request.setAttribute("boardList", boardList);
			
			//최신글 3개를 담은 배열 생성
			if(boardList.size() >= 3) {
				Board[] newBoards = {boardList.get(0), boardList.get(1), 
						boardList.get(2)};
								
				//모델 생성
				request.setAttribute("boardList", newBoards);
			}
			nextPage = "/main.jsp";
		}else if(command.equals("/memberList.do")) {
			List<Member> memberList = memberDAO.getMemberList();
			request.setAttribute("memberList", memberList);
			nextPage = "/member/memberList.jsp";
		}else if(command.equals("/joinForm.do")) {
			nextPage = "member/joinForm.jsp";
		}else if(command.equals("/addMember.do")) {
			//데이터 받아서 저장
			Member member = new Member();
			member.setId(request.getParameter("id"));
			member.setPasswd(request.getParameter("passwd"));
			member.setName(request.getParameter("name"));
			member.setEmail(request.getParameter("email"));
			member.setGender(request.getParameter("gender"));
			
			//db에 저장
			memberDAO.addMember(member);
			//회원 가입후 자동 로그인
			session.setAttribute("sessionId", member.getId());
			session.setAttribute("sessionName", member.getName());
			
			nextPage = "/index.jsp";
		}else if(command.equals("/memberView.do")) {
			String id = request.getParameter("id");
			Member member = memberDAO.getMember(id);
			
			request.setAttribute("member", member);
			nextPage = "/member/memberView.jsp";
		}else if(command.equals("/loginForm.do")) {
			nextPage = "/member/loginForm.jsp";
		}else if(command.equals("/login.do")) {
			Member member = new Member();
			String id = request.getParameter("id");
			String pw = request.getParameter("passwd"); 
			member.setId(id);
			member.setPasswd(pw);
			
			Member m = memberDAO.checkLogin(member); //아이디, 비번 체크하기
			String name = m.getName();   //이름 가져오기
			
			if(name != null) {
				session.setAttribute("sessionId", id);     //세션 아이디 발급
				session.setAttribute("sessionName", name); //세션 이름 발급
				nextPage = "/index.jsp";
			}else {
				out.println("<script>");
				out.println("alert('아이디와 비밀번호를 확인해주세요')");
				out.println("history.go(-1)"); //이전 페이지로 이동
				out.println("</script>");
			}
		}else if(command.equals("/logout.do")) {
			session.invalidate();
			nextPage = "/index.jsp";
		}else if(command.equals("/deleteMember.do")) {
			//회원 탈퇴 처리
			memberDAO.deleteMember(request.getParameter("id"));
			//회원 탈퇴후 세션 삭제
			session.invalidate();
			nextPage = "/memberList.do";
		}else if(command.equals("/memberUpdateForm.do")) {
			String id = request.getParameter("id");
			Member member = memberDAO.getMember(id);
			request.setAttribute("member", member);
			nextPage = "/member/memberUpdateForm.jsp";
		}else if(command.equals("/memberUpdateProcess.do")) {
			String id = request.getParameter("id");
			
			Member updateMember = new Member();
			updateMember.setId(id);
			updateMember.setPasswd(request.getParameter("passwd"));
			updateMember.setName(request.getParameter("name"));
			updateMember.setEmail(request.getParameter("email"));
			updateMember.setGender(request.getParameter("gender"));
			
			memberDAO.updateMember(updateMember);
			nextPage = "/memberView.do?id=" + id;
		}
		
		//게시판
		if(command.equals("/boardList.do")) {
			//페이지 처리
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null) { //pageNum이 없으면 기본 1페이지
				pageNum = "1";
			}
			//각 페이지의 첫행 : 1page->01번, 2page->11, 3->21
			int currentPage = Integer.parseInt(pageNum);
			int pageSize = 10;
			int startRow = (currentPage - 1) * pageSize + 1;
			
			//시작 페이지 : 13번->2, 23->3
			int startPage = startRow / pageSize + 1;
			
			//종료(끝) 페이지
			int totalRow = boardDAO.getBoardCount(); //총행수가 나누어 떨어지면 않으면 페이지수에 1을 더함
			//int endPage = toatal / pageSize -> 3page
			int endPage = totalRow / pageSize;  //총행수 / 페이지당 행의 수
			endPage = (totalRow % pageSize == 0) ? endPage : endPage + 1;
			
			//검색 처리
			//_field, _kw - 임시변수 선언
			String _field = request.getParameter("field");
			String _kw = request.getParameter("kw");
			
			String field = ""; 
			String kw = ""; 
			
			if(_field != null) { //쿼리값이 있는 경우
				field = _field;
			}else {
				field = "title"; //쿼리값이 없는 경우(기본)
			}
			
			if(_kw != null) { //쿼리값이 있는 경우
				kw = _kw;
			}else {
				kw = "";  //쿼리값이 없는 경우(기본)
			}
			
			//게시글 목록(페이지처리)
			//List<Board> boardList = boardDAO.getBoardList(currentPage);
			//게시글 목록(검색)
			//List<Board> boardList = boardDAO.getBoardList(field, kw);
			//게시글 목록(검색, 페이지처리)
			List<Board> boardList = boardDAO.getBoardList(field, kw, currentPage);
			
			//댓글 수 조회

			//모델 생성
			request.setAttribute("boardList", boardList);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("field", field);
			request.setAttribute("kw", kw);
			
			nextPage = "/board/boardList.jsp";
		}else if(command.equals("/writeForm.do")) {
			nextPage = "/board/writeForm.jsp";
		}else if(command.equals("/addBoard.do")) {
			/*String title = request.getParameter("title");
			String content = request.getParameter("content");
			String id = (String)session.getAttribute("sessionId");*/
			
			//일반 name 속성 - request대신 multi로 데이터 받음
			String realFolder = "D:\\yong-jakarta\\members2\\src\\main\\webapp\\upload";
			MultipartRequest multi = new MultipartRequest(request, realFolder,
					5*1024*1024, "utf-8", new DefaultFileRenamePolicy());
					
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			String id = (String)session.getAttribute("sessionId");
			
			//filename 속성 가져오기
			Enumeration<?> files = multi.getFileNames();
			String filename = "";
			if(files.hasMoreElements()) {
				String name = (String)files.nextElement();  //속성에서 가져온 파일이름
				filename = multi.getFilesystemName(name); //서버에 저장할 파일이름
			}
			
			Board board = new Board();
			board.setTitle(title);
			board.setContent(content);
			board.setFilename(filename);
			board.setId(id);
			
			//게시글 추가
			boardDAO.addBoard(board);
		}else if(command.equals("/boardView.do")) {
			int bno = Integer.parseInt(request.getParameter("bno"));
			//게시글 1개 보기
			Board board = boardDAO.getBoard(bno);
			//게시글에서 댓글 작성후 댓글 수 업데이트
			boardDAO.updateReplycnt(bno);
			//댓글 목록 보기
			List<Reply> replyList = replyDAO.getReplyList(bno);
			
			request.setAttribute("board", board);
			request.setAttribute("replyList", replyList);
			nextPage = "/board/boardView.jsp";
		}else if(command.equals("/deleteBoard.do")) {
			int bno = Integer.parseInt(request.getParameter("bno"));
			boardDAO.deleteBoard(bno);
		}else if(command.equals("/updateBoardForm.do")) {
			int bno = Integer.parseInt(request.getParameter("bno"));
			//수정할 게시글 가져오기
			Board board = boardDAO.getBoard(bno);
			request.setAttribute("board", board);
			nextPage = "/board/updateBoardForm.jsp";
		}else if(command.equals("/updateBoard.do")) {
			int bno = Integer.parseInt(request.getParameter("bno"));
			//글쓴이는 넘겨 받지 않아도 가능함
			Board board = new Board();
			board.setBno(bno);
			board.setTitle(request.getParameter("title"));
			board.setContent(request.getParameter("content"));
			
			//게시글 수정 처리
			boardDAO.updateBoard(board);
		}else if(command.equals("/vote.do")) {
			int bno = Integer.parseInt(request.getParameter("bno"));
			boardDAO.updateVoter(bno);
		}
		
		//댓글
		if(command.equals("/insertreply.do")) {
			int bno = Integer.parseInt(request.getParameter("bno"));
			String rcontent = request.getParameter("rcontent");
			String replyer = request.getParameter("replyer");
			
			Reply reply = new Reply();
			reply.setBno(bno);
			reply.setRcontent(rcontent);
			reply.setReplyer(replyer);
			
			replyDAO.insertReply(reply);
		}else if(command.equals("/deletereply.do")) {
			int rno = Integer.parseInt(request.getParameter("rno"));
			
			replyDAO.deleteReply(rno);
		}else if(command.equals("/updateReplyform.do")) {
			int rno = Integer.parseInt(request.getParameter("rno"));
			Reply reply = replyDAO.getReply(rno);
			
			request.setAttribute("reply", reply);
			
			nextPage = "/board/updateReplyform.jsp";
		}else if(command.equals("/updatereply.do")) {
			int rno = Integer.parseInt(request.getParameter("rno"));
			String rcontent = request.getParameter("rcontent");
			//replyer는 넘겨 받지 않아도 가능함
			
			Reply reply = new Reply();
			reply.setRno(rno);
			reply.setRcontent(rcontent);
			//댓글 수정처리
			replyDAO.updateReply(reply);
		}
		
		//경로 요청 > 페이지 이동
		if(command.equals("/addBoard.do") || command.equals("/deleteBoard.do")
				|| command.equals("/updateBoard.do")) {
			response.sendRedirect("/boardList.do");
		}else if(command.equals("/insertreply.do") || command.equals("/deletereply.do")
				|| command.equals("/updatereply.do") || command.equals("/vote.do")) {
			int bno = Integer.parseInt(request.getParameter("bno"));
			response.sendRedirect("/boardView.do?bno=" + bno);
		}else {
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher(nextPage);
			
			dispatcher.forward(request, response);
		}
	}

}
