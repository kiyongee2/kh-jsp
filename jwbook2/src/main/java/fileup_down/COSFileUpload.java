package fileup_down;

import java.io.File;
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

@WebServlet("/cos")
public class COSFileUpload extends HttpServlet {
	private static final long serialVersionUID = 20L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		//파일이 저장되는 위치
		ServletContext context = getServletContext();
		String realFolder = context.getRealPath("uploaded_files");
		
		//MultipartRequest의 인자
		int maxSize = 10*1024*1024;
		String encType = "utf-8";
		DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
		
		try {
			MultipartRequest multi
				= new MultipartRequest(request, realFolder, maxSize, encType, policy);
			
			//일반 텍스트 파라미터 추출
			Enumeration<?> params = multi.getParameterNames();
			
			while(params.hasMoreElements()) {
				String paramName = (String)params.nextElement();
				String paramValue = multi.getParameter(paramName);
				out.println(paramName + " : " + paramValue + "<br>");
			}
			
			//file 파라미터 추출
			Enumeration<?> files = multi.getFileNames();
			
			while(files.hasMoreElements()) {
				//파라미터 이름 추출
				String userFilename = (String)files.nextElement();
				
				//업로드된(파일이름 변경 전) 파일 이름 추출
				String originalFilename = multi.getOriginalFileName(userFilename);
				
				//파일이 업로드된 경우 수행
				if(originalFilename != null) {
					//변경전 파일 이름 출력
					out.println("OriginalFilename : " + originalFilename + "<br>");
					
					//같은 이름인 경우 변경되어 저장된 파일 이름
					String fileSystemname = multi.getFilesystemName(userFilename);
			
					out.println("fileSystemname : " + fileSystemname + "<br>");
					out.println("<a href=down.do?filename=" + fileSystemname + ">파일다운</a><br><br>");
					
					//업로드된 파일의 File 객체 생성
					File userFile = multi.getFile(userFilename);
					
					int fileSize = (int) userFile.length();
					
					//파일의 크기와 타입 추출
					if(fileSize > 0) {
						out.println("크기: " + fileSize + "B<br>");
						out.println("유형: " + multi.getContentType(userFilename));
						out.println("<p>============================</p>");
					}
				}//originalFilename if 끝
			}//file 파라미터 while 끝
		}catch(Exception e) {
			e.printStackTrace();
		}
	}//doPost 끝
}
