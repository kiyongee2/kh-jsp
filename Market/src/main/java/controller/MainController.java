package controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
			//List<Product> products = pdao.getProductList();
			//검색 처리
			//_field, _kw - 임시변수 선언
			String _field = request.getParameter("field");
			String _kw = request.getParameter("kw");
			
			String field = ""; 
			String kw = ""; 
			
			if(_field != null) { //쿼리값이 있는 경우
				field = _field;
			}else {
				field = "p_name"; //쿼리값이 없는 경우(기본)
			}
			
			if(_kw != null) { //쿼리값이 있는 경우
				kw = _kw;
			}else {
				kw = "";  //쿼리값이 없는 경우(기본)
			}
		
			List<Product> products = pdao.getProductList(field, kw);
			
			request.setAttribute("products", products);
			request.setAttribute("field", field);
			request.setAttribute("kw", kw);
			nextPage = "/product/list.jsp";
		}else if(command.equals("/productform.do")) {
			nextPage = "/product/pform.jsp";
		}else if(command.equals("/insertproduct.do")) {
			//데이터 받기
			String realFolder = "D:/yong-jakarta/Market/src/main/webapp/upload";

			MultipartRequest multi = new MultipartRequest(request, realFolder,
					10*1024*1024, new DefaultFileRenamePolicy());
			
			//일반 name 받아오기
			String pid = multi.getParameter("pid");
			String pname = multi.getParameter("pname");
			pname = new String(pname.getBytes("8859_1"), "utf-8");  //한글 인코딩
			int price = Integer.parseInt(multi.getParameter("price"));
			String description = multi.getParameter("description");
			description = new String(description.getBytes("8859_1"), "utf-8");
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
		}else if(command.equals("/productinfo.do")) {
			String pid = request.getParameter("pid");
			Product product = pdao.getProduct(pid);
			request.setAttribute("product", product);
			nextPage = "/product/pinfo.jsp";
		}else if(command.equals("/editproduct.do")) {
			List<Product> products = pdao.getProductList();
			String edit = request.getParameter("edit");
			
			request.setAttribute("products", products);
			request.setAttribute("edit", edit);
			
			nextPage = "/product/edit.jsp";
		}else if(command.equals("/deleteproduct.do")) {
			String pid = request.getParameter("pid");
			pdao.deleteProduct(pid);
			nextPage = "/editproduct.do?edit=delete";
		}
		
		else if(command.equals("/addcart.do")) {
			String pid = request.getParameter("pid");
			
			//상품 목록
			List<Product> goodsList = pdao.getProductList();
			Product goods = new Product();
			
			//목록에서 추가한 상품 찾기
			for(int i=0; i<goodsList.size(); i++) {
				goods = goodsList.get(i);
				if(goods.getPid().equals(pid)) {
					break;
				}
			}
			
			//장바구니 생성 및 세션 발급
			List<Product> list = (ArrayList<Product>)session.getAttribute("cartlist");
			if(list == null) {
				list = new ArrayList<>();
				session.setAttribute("cartlist", list);
			}
			
			//요청 아이디의 상품이 기존에 장바구니에 있으면 수량 증가
			int cnt = 0;
			Product goodsQnt = new Product();
			
			for(int i=0; i<list.size(); i++) {
				goodsQnt = list.get(i);
				if(goodsQnt.getPid().equals(pid)) {
					cnt++; //추가한 횟수
					int orderQuantity = goodsQnt.getQuantity() + 1; //주문 수량
					goodsQnt.setQuantity(orderQuantity);
				}
			}
			//장바구니에 추가한 적이 없으면 수량을 1로 정하고 장바구니 목록에 추가
			if(cnt == 0) {
				goods.setQuantity(1);
				list.add(goods);
			}
		}else if(command.equals("/cart.do")) {
			//장바구니 리스트 생성 및 세션 유지하기
			List<Product> cartList = (ArrayList<Product>)session.getAttribute("cartlist");
			if(cartList == null) {
				cartList = new ArrayList<>();
			}
			
			int sum = 0;     //총합계
			int unit_sum = 0;   //품목별 합계
			
			for(int i=0; i < cartList.size(); i++) {
				Product product = cartList.get(i);
				unit_sum = product.getPrice() * product.getQuantity(); //가격*수량
				sum += unit_sum;
			}
			
			//주문하기에 필요한 cartId 생성
			String cartId = session.getId();
			
			request.setAttribute("cartId", cartId);
			request.setAttribute("cartList", cartList);
			request.setAttribute("sum", sum);
			
			nextPage = "/product/cart.jsp";
		}else if(command.equals("/removecart.do")) {
			String pid = request.getParameter("pid");
			List<Product> cartList = (ArrayList<Product>)session.getAttribute("cartlist");
			//삭제할 품목 객체 생성
			Product product = new Product();
			for(int i=0; i<cartList.size(); i++) {
				product = cartList.get(i);
				if(product.getPid().equals(pid)) { //삭제 요청 아이디와 일치하면
					cartList.remove(product);
				}
			}
		}else if(command.equals("/deletecart.do")) {
			//장바구니 전체 비우기 (세션 삭제)
			session.invalidate();
		}else if(command.equals("/shippingform.do")) {
			String cartId = session.getId();
			request.setAttribute("cartId", cartId);
			nextPage = "/product/shippingform.jsp";
		}else if(command.equals("/shippinginfo.do")) {
			//쿠키 발행
			Cookie shippingId = new Cookie("Shipping_cartId", 
					URLEncoder.encode(request.getParameter("cartId"), "utf-8"));
			Cookie name = new Cookie("Shipping_name", 
					URLEncoder.encode(request.getParameter("name"), "utf-8"));
			Cookie shippingdate = new Cookie("Shipping_shippingdate", 
					URLEncoder.encode(request.getParameter("shippingdate"), "utf-8"));
			Cookie country = new Cookie("Shipping_country", 
					URLEncoder.encode(request.getParameter("country"), "utf-8"));
			Cookie zipcode = new Cookie("Shipping_zipcode", 
					URLEncoder.encode(request.getParameter("zipcode"), "utf-8"));
			Cookie address = new Cookie("Shipping_address", 
					URLEncoder.encode(request.getParameter("address"), "utf-8"));

			//유효 기간 1일
			shippingId.setMaxAge(24*60*60);
			name.setMaxAge(24*60*60);
			shippingdate.setMaxAge(24*60*60);
			country.setMaxAge(24*60*60);
			zipcode.setMaxAge(24*60*60);
			address.setMaxAge(24*60*60);
			
			//클라이언트로 쿠키 보내기
			response.addCookie(shippingId);
			response.addCookie(name);
			response.addCookie(shippingdate);
			response.addCookie(country);
			response.addCookie(zipcode);
			response.addCookie(address);
			
			//쿠키 정보 가져와서 모델 만들기
			String shipping_cartId = "";
			String shipping_name = "";
			String shipping_shippingdate = "";
			String shipping_country = "";
			String shipping_zipcode = "";
			String shipping_address = "";
			
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for(int i=0; i<cookies.length; i++) {
					Cookie cookie = cookies[i];
					String cname = cookie.getName(); 
					if(cname.equals("Shipping_cartId"))
						shipping_cartId = URLDecoder.decode(cookie.getValue(), "utf-8");
					if(cname.equals("Shipping_name"))
						shipping_name = URLDecoder.decode(cookie.getValue(), "utf-8");
					if(cname.equals("Shipping_shippingdate"))
						shipping_shippingdate = URLDecoder.decode(cookie.getValue(), "utf-8");
					if(cname.equals("Shipping_country"))
						shipping_country = URLDecoder.decode(cookie.getValue(), "utf-8");
					if(cname.equals("Shipping_zipcode"))
						shipping_zipcode = URLDecoder.decode(cookie.getValue(), "utf-8");
					if(cname.equals("Shipping_address"))
						shipping_address = URLDecoder.decode(cookie.getValue(), "utf-8");
					
				}
			}
			
			//장바구니 리스트 생성 및 세션 유지하기
			List<Product> cartList = (ArrayList<Product>)session.getAttribute("cartlist");
			if(cartList == null) {
				cartList = new ArrayList<>();
			}
			
			int sum = 0;     //총합계
			int unit_sum = 0;   //품목별 합계
			
			for(int i=0; i < cartList.size(); i++) {
				Product product = cartList.get(i);
				unit_sum = product.getPrice() * product.getQuantity(); //가격*수량
				sum += unit_sum;
			}
			
			//모델 생성(배송)
			request.setAttribute("shipping_name", shipping_name);
			request.setAttribute("shipping_shippingdate", shipping_shippingdate);
			request.setAttribute("shipping_zipcode", shipping_zipcode);
			request.setAttribute("shipping_address", shipping_address);
			
			//모델 생성(상품)
			request.setAttribute("cartList", cartList);
			request.setAttribute("sum", sum);
			
			nextPage = "/product/orderconfirm.jsp";
		}else if(command.equals("/thankscustomer.do")) {
			String shipping_cartId = "";
			String shipping_shippingdate = "";
			
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for(int i=0; i<cookies.length; i++) {
					Cookie cookie = cookies[i];
					String cname = cookie.getName(); 
					if(cname.equals("Shipping_cartId"))
						shipping_cartId = URLDecoder.decode(cookie.getValue(), "utf-8");
					if(cname.equals("Shipping_shippingdate"))
						shipping_shippingdate = URLDecoder.decode(cookie.getValue(), "utf-8");
				}
			}
			
			nextPage = "/product/thankscustomer.jsp";
			
			request.setAttribute("shipping_cartId", shipping_cartId);
			request.setAttribute("shipping_shippingdated", shipping_shippingdate);
			
			//모든 세션 삭제
			session.invalidate();
			
			//모든 쿠키 삭제
			if(cookies != null) {
				for(int i=0; i<cookies.length; i++) {
					Cookie cookie = cookies[i];
					String cname = cookie.getName(); 
					if(cname.equals("Shipping_cartId"))
						cookie.setMaxAge(0);
					if(cname.equals("Shipping_name"))
						cookie.setMaxAge(0);
					if(cname.equals("Shipping_shippingdate"))
						cookie.setMaxAge(0);
					if(cname.equals("Shipping_country"))
						cookie.setMaxAge(0);
					if(cname.equals("Shipping_zipcode"))
						cookie.setMaxAge(0);
					if(cname.equals("Shipping_address"))
						cookie.setMaxAge(0);
				}
			}
			
		}
		
		//redirect, forward 구분
		if(command.equals("/insertproduct.do")) {
			response.sendRedirect("/productlist.do");
		}else if(command.equals("/addcart.do")) {
			String pid = request.getParameter("pid");
			response.sendRedirect("/productinfo.do?pid=" + pid);
		}else if(command.equals("/removecart.do") || command.equals("/deletecart.do")) {
			response.sendRedirect("/cart.do");
		}else {
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher(nextPage);
			
			dispatcher.forward(request, response);
		}
	}

}
