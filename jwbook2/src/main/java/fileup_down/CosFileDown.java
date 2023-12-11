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

@WebServlet("/cosdown.do")
public class CosFileDown extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String filename = request.getParameter("filename");
			
			ServletContext context = getServletContext();
			String realFolder = context.getRealPath("uploaded_files");
			
			String realFile = realFolder + "\\" + filename;
			
			File file = new File(realFile);
			response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");
			response.setContentLength((int)file.length());
			response.setHeader("Cache-control", "no-cache");
			
			FileInputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();
			
			byte[] data = new byte[1024*1024];
			
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


}
