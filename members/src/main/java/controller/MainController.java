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

@WebServlet("*.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberDAO memberDAO;
	BoardDAO boardDAO;
       
    public MainController() {
        memberDAO = new MemberDAO();
        boardDAO = new BoardDAO();
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
		String command = uri.substring(uri.lastIndexOf('/'));
		System.out.println(uri);
		System.out.println(uri.lastIndexOf('/'));
		
		//이동할 페이지
		String nextPage = null;
		
		//세션 객체
		HttpSession session = request.getSession();
		
		//출력 스트림 객체 생성
		PrintWriter out = response.getWriter();
		
		if(command.equals("/index.do")) {
			//게시글 가져오기
			List<Board> boardList = boardDAO.getBoardList();
			int size = boardList.size(); //게시글의 총수
			//최신글 3개를 담은 배열 생성
			Board[] boardArray = {boardList.get(size-1), boardList.get(size-2), 
					boardList.get(size-3)};
			
			//모델 생성
			request.setAttribute("boardList", boardArray);
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
			
			String name = memberDAO.getNameById(id);   //이름 가져오기
			boolean result = memberDAO.checkLogin(member); //아이디, 비번 체크하기
			
			if(result) {
				session.setAttribute("sessionId", id);     //세션 아이디 발급
				session.setAttribute("sessionName", name); //세션 이름 발급
				nextPage = "/index.jsp";
			}else {
				/*String error = "아이디나 비밀번호가 일치하지 않습니다.";
				request.setAttribute("error", error);
				response.sendRedirect("/member/loginForm.jsp");*/
				out.println("<script>");
				out.println("alert('아이디와 비밀번호를 확인해주세요')");
				out.println("history.go(-1)"); //이전 페이지로 이동
				out.println("</script>");
			}
		}else if(command.equals("/logout.do")) {
			session.invalidate();
			nextPage = "/index.jsp";
		}else if(command.equals("/deleteMember.do")) {
			memberDAO.deleteMember(request.getParameter("id"));
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
			List<Board> boardList = boardDAO.getBoardList();
			request.setAttribute("boardList", boardList);
			nextPage = "/board/boardList.jsp";
		}else if(command.equals("/writeForm.do")) {
			nextPage = "/board/writeForm.jsp";
		}else if(command.equals("/addBoard.do")) {
			/*String title = request.getParameter("title");
			String content = request.getParameter("content");
			String id = (String)session.getAttribute("sessionId");*/
			
			//일반 name 속성 - request대신 multi로 데이터 받음
			String realFolder = "D:\\yong-jakarta\\members\\src\\main\\webapp\\upload";
			MultipartRequest multi = new MultipartRequest(request, realFolder,
					5*1024*1024, "utf-8", new DefaultFileRenamePolicy());
					
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			String id = (String)session.getAttribute("sessionId");
			
			//filename 속성 가져오기
			Enumeration<String> files = multi.getFileNames();
			String filename = "";
			if(files.hasMoreElements()) {
				String name = files.nextElement();  //속성에서 가져온 파일이름
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
			Board board = boardDAO.getBoard(bno);
			request.setAttribute("board", board);
			nextPage = "/board/boardView.jsp";
		}else if(command.equals("/deleteBoard.do")) {
			int bno = Integer.parseInt(request.getParameter("bno"));
			boardDAO.deleteBoard(bno);
		}else if(command.equals("/updateBoardForm.do")) {
			int bno = Integer.parseInt(request.getParameter("bno"));
			Board board = boardDAO.getBoard(bno);
			request.setAttribute("board", board);
			nextPage = "/board/updateBoardForm.jsp";
		}else if(command.equals("/updateBoard.do")) {
			int bno = Integer.parseInt(request.getParameter("bno"));
			
			Board board = new Board();
			board.setBno(bno);
			board.setTitle(request.getParameter("title"));
			board.setContent(request.getParameter("content"));
			
			boardDAO.updateBoard(board);
		}
		
		//경로 요청 > 페이지 이동
		//redirect와 forward로 분기
		if(command.equals("/addBoard.do") || command.equals("/deleteBoard.do")
				|| command.equals("/updateBoard.do")) {
			response.sendRedirect("/boardList.do");
		}else {
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher(nextPage);
			
			dispatcher.forward(request, response);
		}
	}

}
