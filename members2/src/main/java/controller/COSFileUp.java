package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/fileup")
public class COSFileUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public COSFileUp() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		//파일이 저장되는 위치
		//ServletContext context = getServletContext();
		//String realFolder = context.getRealPath("uploaded_files");
		String realFolder = "D:\\yong-jakarta\\jwbook2\\src\\main\\webapp\\uploaded_files";
		
		//MultipartRequest의 인자
		int maxSize = 10*1024*1024;
		String encType = "utf-8";
		DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
		
		MultipartRequest multi
			= new MultipartRequest(request, realFolder, maxSize, encType, policy);
		
		//일반 텍스트 파라미터 추출
		/*Enumeration<?> params = multi.getParameterNames();
		
		while(params.hasMoreElements()) {
			String paramName = (String)params.nextElement();
			String paramValue = multi.getParameter(paramName);
			out.println(paramName + " : " + paramValue + "<br>");
		}*/
		String comment = multi.getParameter("comment");
		System.out.println("설명: " + comment);
		
		//file 파라미터 추출
		Enumeration<?> files = multi.getFileNames();
		
		while(files.hasMoreElements()) {
			//파라미터 이름 추출
			String userFilename = (String)files.nextElement();
			
			//업로드된(파일이름 변경 전) 파일 이름 추출
			String originalFilename = multi.getOriginalFileName(userFilename);
			out.println("OriginalFilename : " + originalFilename + "<br>");
			
			//같은 이름인 경우 변경되어 저장된 파일 이름
			String fileSystemname = multi.getFilesystemName(userFilename);
	
			//out.println("fileSystemname : " + fileSystemname + "<br>");
			out.println("<a href=down.do?filename=" + fileSystemname + ">파일다운</a><br><br>");
		}		
	}
}
