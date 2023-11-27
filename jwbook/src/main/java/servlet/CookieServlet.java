package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookie")
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//브라우저로 응답(내보내기) - 한글 인코딩
		response.setContentType("text/html; charset=utf-8");
		
		Cookie cookie = new Cookie("cookieTest", URLEncoder.encode("JSP프로그래밍", "utf-8"));
		cookie.setMaxAge(24*60*60);
		
		response.addCookie(cookie);

		PrintWriter out = response.getWriter();
		out.println("쿠키가 생성되었습니다.");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
