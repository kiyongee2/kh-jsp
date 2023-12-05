package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/data")
public class DataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DataServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//변수
		String error = "아이디 또는 비밀번호를 확인해주세요";
		
		//리스트
		List<String> carts= new ArrayList<>();
		carts.add("두부");
		carts.add("커피");
		carts.add("콩나물");
		carts.add("쌀");
		
		//Map 자료구조
		Map<String, Integer> subjects = new HashMap<>();
		subjects.put("국어", 90);
		subjects.put("수학", 80);
		subjects.put("과학", 85);
		
		//모델
		request.setAttribute("error", error);
		request.setAttribute("carts", carts);
		request.setAttribute("subjects", subjects);
		
		//페이지 포워딩
		RequestDispatcher dispatch =
				request.getRequestDispatcher("/mvc/mvc01.jsp");
		dispatch.forward(request, response);
	}

}
