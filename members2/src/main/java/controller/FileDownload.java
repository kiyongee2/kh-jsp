package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/filedown")
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FileDownload() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 값추출
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		//3. 비즈니스로직
		NoticeService service = new NoticeService();
		Notice n = service.getNotice(noticeNo);//게시글 번호를 받아서 게시글 전체조회(filename, filepath 값필요하므로)
		//filename : 업로드시 사용자가 올린 파일 원본이름
		//filepath : 파일 저장시 파일명중복처리 한 파일명
		//4. 결과처리
		//파일과 현재 서블릿을 연결
		String root = getServletContext().getRealPath("/");///webapp경로 읽어오기
		String downLoadFile = root+"upload/notice/"+ n.getFilepath();//webapp폴더기준으로 저장된 파일위치
		//파일을 서블릿으로 읽어오기위한 스트림생성
		FileInputStream fis = new FileInputStream(downLoadFile);
		BufferedInputStream bis = new BufferedInputStream(fis);
		//읽어온파일을 사용자에게 전달할 스트림 생성
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos);
		//파일명처리
		//브라우저에따른 파일이름 처리
		String resFilename = ""; //최종 다운로드할 파일이름
		//브라우저가 IE확인
		boolean bool = 
				request.getHeader("user-agent").indexOf("MSIE") != -1 ||
				request.getHeader("user-agent").indexOf("Trident") != -1;
		System.out.println("IE 여부 : "+bool);
		if(bool) {//브라우저가 IE인경우
			resFilename = URLEncoder.encode(n.getFilename(),"UTF-8");
			resFilename = resFilename.replaceAll("\\\\", "%20");//\\\\
		}else {//그외 다른 브라우저인 경우
			resFilename = new String(n.getFilename().getBytes("UTF-8"),"ISO-8859-1");
		}
		//파일다운로드를위한 HTTP Header 설정
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="+resFilename);
		//파일전송
		while(true) {
			int read = bis.read();
			if(read != -1) {
				bos.write(read);
			}else {
				break;
			}
		}
		bos.close();
		bis.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
