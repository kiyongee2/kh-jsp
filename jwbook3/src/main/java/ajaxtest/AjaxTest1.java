package ajaxtest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ajaxtest/ajax1")
public class AjaxTest1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxTest1() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String msg = request.getParameter("message");
		
		out.println(msg);
		//out.println("테스트");
	}

}
