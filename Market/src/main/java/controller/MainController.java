package controller;

import java.io.IOException;
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

import model.Product;
import model.ProductDAO;

@WebServlet("*.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ProductDAO pdao;   
 
    public MainController() {
      pdao = new ProductDAO();
    }

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

		
		if(command.equals("/main.do")) {
			nextPage = "/main.jsp";
		}else if(command.equals("/productlist.do")) {
			List<Product> products = pdao.getProductList();
			request.setAttribute("products", products);
			nextPage = "/product/list.jsp";
		}else if(command.equals("/productdetail.do")) {
			String pid = request.getParameter("pid");
			nextPage = "/product/detail.jsp";
		}else if(command.equals("/productform.do")) {
			nextPage = "/product/pform.jsp";
		}else if(command.equals("/insertproduct.do")) {
			//데이터 받기
			/*String pid = request.getParameter("pid");
			String pname = request.getParameter("pname");
			int price = Integer.parseInt(request.getParameter("price"));
			String description = request.getParameter("description");
			String category = request.getParameter("category");
			int pstock = Integer.parseInt(request.getParameter("pstock"));
			String condition = request.getParameter("condition");*/
			
			String realFolder = "D:/yong-jakarta/Market/src/main/webapp/upload";

			MultipartRequest multi = new MultipartRequest(request, realFolder,
					10*1024*1024, new DefaultFileRenamePolicy());
			
			//일반 name 받아오기
			String pid = multi.getParameter("pid");
			String pname = multi.getParameter("pname");
			pname = new String(pname.getBytes("8859_1"), "utf-8");  //한글 인코딩
			int price = Integer.parseInt(multi.getParameter("price"));
			String description = multi.getParameter("description");
			String category = multi.getParameter("category");
			int pstock = Integer.parseInt(multi.getParameter("pstock"));
			String condition = multi.getParameter("condition");
			
			//이미지 파일 받아오기
			Enumeration<?> files = multi.getFileNames();
			String pimage = "";
			while(files.hasMoreElements()){
				String name = (String)files.nextElement();
				pimage = multi.getFilesystemName(name);
			}
			
			Product p = new Product();
			p.setPid(pid);
			p.setPname(pname);
			p.setPrice(price);
			p.setDescription(description);
			p.setCategory(category);
			p.setPstock(pstock);
			p.setCondition(condition);
			p.setPimage(pimage);
			
			pdao.insertProduct(p);
			
			nextPage = "/productlist.do";
		}
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher(nextPage);
		
		dispatcher.forward(request, response);
	}

}
