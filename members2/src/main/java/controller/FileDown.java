package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/filedown.do")
public class FileDown extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FileDown() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//전달된 파일의 이름 추출
			String filename = request.getParameter("filename");
			
			//파일이 저장되는 위치
			ServletContext context = getServletContext();
			String realFolder = context.getRealPath("upload");
			
			//저장될 위치와 파일 이름을 표현
			String dirAndFilename = realFolder + "\\" + filename;
			
			//업로드되는 파일의 File 객체 생성
			File file = new File(dirAndFilename);
			
			//파일이름에 한글이 포함되어 있는 경우 인코딩 지정
			filename = new String(filename.getBytes("utf-8"), "ISO-8859-1");
			
			//파일 다운로드를 위한 헤더 설정
			response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");
			response.setContentLength((int)file.length());
			response.setHeader("Cache-control", "no-cache");
			
			//파일 입력스트림과 출력 스트림 생성
			FileInputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();
			
			//버퍼 생성
			byte[] data = new byte[1024*1024];
			
			//파일 출력 수행
			int count = -1;
			while((count = in.read(data)) != -1) {
				out.write(data, 0, count);
			}
			
			in.close();
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
