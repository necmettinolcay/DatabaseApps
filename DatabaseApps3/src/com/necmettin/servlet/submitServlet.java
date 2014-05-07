package com.necmettin.servlet;

import java.io.IOException;  
import java.io.InputStream;  
  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;
import java.sql.SQLException;  
import java.text.ParseException;
import java.text.SimpleDateFormat;





import javax.servlet.ServletException;  
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.Part;  

import com.mysql.jdbc.Blob;
import com.necmettin.util.DbUtil;  



/**
 * Servlet implementation class submitServlet
 */
@WebServlet("/submitServlet")
public class submitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		java.sql.Blob image = (java.sql.Blob) request.getSession().getAttribute("Image");
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("image/gif");
		
		InputStream in = null;
		try {
			in = image.getBinaryStream();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int length;
		try {
			length = (int) image.length();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int bufferSize = 4096;
		byte[] buffer = new byte[bufferSize];
		

		while((length = in.read(buffer)) != -1 ){
			out.write(buffer,0,length);
		
		}
		in.close();
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//java.sql.Blob image = (Blob) request.getAttribute("Image");
		//response.setContentType("text/html");
		
		
		
		
	}

}
