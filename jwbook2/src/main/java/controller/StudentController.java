package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Student;
import model.StudentDAO;

@WebServlet("/")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	StudentDAO studentDAO;
       
    public StudentController() {
        studentDAO = new StudentDAO();
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
		
		//이동할 페이지
		String nextPage = null;
		
		if(command.equals("/studentlist")) {
			List<Student> students = studentDAO.getStudentList();
			request.setAttribute("students", students);
			nextPage = "/student/studentlist.jsp";
		}else if(command.equals("/studentform")) {
			nextPage = "/student/studentform.jsp";
		}else if(command.equals("/insertstudent")) {
			String username = request.getParameter("username");
			String univ = request.getParameter("univ");
			String birth = request.getParameter("birth");
			String email = request.getParameter("email");
			
			Student s = new Student();
			s.setUsername(username);
			s.setUniv(univ);
			s.setBirth(birth);
			s.setEmail(email);
			
			studentDAO.insertStudent(s);
			
			nextPage = "/studentlist";
		}
		
		RequestDispatcher dispatch =
				request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
