package fileup_down;

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

@WebServlet("/down.do")
public class FileDown extends HttpServlet {
	private static final long serialVersionUID = 21L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//COSFileUp 서블릿에서 GET 방식으로 전달된 다운로드할 파일의 이름을 추출함
			String filename = request.getParameter("filename");
			
			//파일이 저장되는 위치
			ServletContext context = getServletContext();
			String realFolder = context.getRealPath("uploaded_files");
			
			//저장될 위치와 파일 이름을 표현
			String dirAndFilename = realFolder + "\\" + filename;
			
			//업로드되는 파일의 File 객체 생성
			File file = new File(dirAndFilename);
			
			//파일이름에 한글이 포함되어 있는 경우 인코딩 지정
			//filename = new String(filename.getBytes("utf-8"), "ISO-8859-1");
			
			//파일 다운로드를 위한 헤더 설정
			//Content-Disposition-저장형태, attachment-파일 저장, inline-브라우저 저장
			response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");
			//다운로드할 파일의 크기를 클라이언트에 전달
			response.setContentLength((int)file.length());
			//캐시를 허용하지 않음 - 해당파일의 다운로드를 시도하면 서버에서 다운로드하지 않고 캐시의 내용을 실행하려고함
			//따라서 항상 파일이 서버로부터 다운로드 되도록함
			response.setHeader("Cache-control", "no-cache");
			
			//파일 입력스트림과 출력 스트림 생성
			FileInputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();
			
			//버퍼 생성
			byte[] data = new byte[1024*1024];
			
			//파일 출력 수행
			int count = 0;
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
