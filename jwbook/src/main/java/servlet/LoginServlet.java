package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 인코딩(폼에서 받을때)
		request.setCharacterEncoding("utf-8");
		
		//브라우저로 응답(내보내기) - 한글 인코딩
		response.setContentType("text/html; charset=utf-8");
		
		//폼에 입력된 데이터 받기
		String id = request.getParameter("uid");
		String pw = request.getParameter("passwd");
		
		//System.out.println("아이디: " + id);
		//System.out.println("패스워드: " + pw);
		
		//브라우저로 보내기(/jwbook/login)
		PrintWriter out = response.getWriter();
			out.append("<html><body><p>아이디: " + id + "</p>")
			   .append("<p>패스워드: " + pw + "</p></body></html>");
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
