package com.necmettin.servlet;


import java.io.IOException;  
import java.io.InputStream;  
import java.sql.Connection;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;
import java.sql.SQLException;  
import java.text.ParseException;
import java.text.SimpleDateFormat;



import java.util.Date;

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
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
@MultipartConfig(maxFileSize = 16177215)
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private Connection conn;
	
	
    public UploadServlet() {
        conn = DbUtil.getConnection();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String ad = request.getParameter("ad");
		String soyad = request.getParameter("soyad");
		String dtarih = request.getParameter("dtarih");
		String eposta = request.getParameter("eposta");
		String deger = request.getParameter("box1");
		int yetki = 0;
		if("ogretmen".equals(deger)){
			yetki = 1;
		}else{
			yetki = 0;
		}
		java.util.Date date0 = null;
		try {
			date0 = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dtarih"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		java.sql.Date sqlDate0 = new java.sql.Date(date0.getTime());
		java.util.Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
		 
		
		
		
		InputStream inputStream = null;
	//	ServletOutputStream out = response.getOutputStream();
		
		Part filePart =  request.getPart("resim");
		
		if(filePart != null){
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());
			
			inputStream = filePart.getInputStream();
		}
		
		String message = null;
		try{
			String sql = "INSERT INTO tuser (userName, ad, soyad, dogumtarihi, kayitolmatarihi, epostaadresi, userphoto, yetkiId) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username );
			statement.setString(2, ad);
			statement.setString(3, soyad);
			statement.setDate(4, sqlDate0);
			statement.setDate(5, sqlDate);
			statement.setString(6, eposta);
			
			if(inputStream != null){
				statement.setBlob(7, inputStream);
			}
			statement.setInt(8, yetki);
			
			int row = statement.executeUpdate();
			if(row > 0){
				message = "Image is uploaded succesfully into the database";
			
				
				
				
			} 
		}catch (SQLException ex) {  
	            message = "ERROR: " + ex.getMessage();  
	            
		}
		request.setAttribute("Message", message); 
		getServletContext().getRequestDispatcher("/submit.jsp").forward(  
                request, response);  
			
  }
		
}


