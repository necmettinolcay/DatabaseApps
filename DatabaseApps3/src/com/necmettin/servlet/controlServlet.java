package com.necmettin.servlet;

import java.io.IOException;  
import java.io.InputStream;  
import java.io.PrintWriter;
import java.sql.Connection;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;
import java.sql.SQLException;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.Part;  
import javax.swing.JOptionPane;

import com.mysql.jdbc.Blob;
import com.necmettin.util.DbUtil;



/**
 * Servlet implementation class controlServlet
 */
@WebServlet("/controlServlet")
public class controlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    private Connection conn; 
     
    public controlServlet() {
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
		String password = request.getParameter("password");
		String dtbsuser = null;
		int yetki = 0;
		boolean newbie = true;
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie c: cookies){
				if( ( c.getName().equals("username") && c.getValue().equals(username)) ){
					
						newbie = false;
						break;
				}
				
				
			}
		}
		String title ;
		if(newbie){
			Cookie returnVisitorCookie = new Cookie("username", username);
			Cookie returnVisitorCookie1 = new Cookie("password", password);
			returnVisitorCookie.setMaxAge(60*60*24*365);
			returnVisitorCookie1.setMaxAge(60*60*24*365);
			response.addCookie(returnVisitorCookie);
			response.addCookie(returnVisitorCookie1);
			title = "Welcome Aboard";
			
		}else{
			title = "Welcome back";
		}
		
		
		
		
		String message = null;
		java.sql.Blob image = null;
		try{
			String sql = "select userName, yetkiId, userphoto from tuser where userName='" + username + "'";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()){
				dtbsuser = rs.getString("userName");
				yetki = rs.getInt("yetkiId");
				image = rs.getBlob("userphoto");
				
			}
			if(username.equals(dtbsuser)){
				if( yetki != 1 ){
					message = "Ogrenci ekrani";
						
				}
				else{
					message = "Ogretmen ekrani";
				}
				
				request.setAttribute("User", dtbsuser);
				request.setAttribute("Message", message);
				request.setAttribute("Title", title);
				request.setAttribute("Password", password);
				
				request.getSession().setAttribute("Image", image);
				request.getSession().setAttribute("user", username);
				request.getSession().setAttribute("password", password);
				
		
				
				
				getServletContext().getRequestDispatcher("/submitServletmain").forward(  
		                request, response);
				
			}else{
				
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
				PrintWriter out1= response.getWriter();
				
				out1.println("<script language=javascript>alert('Sisteme kayit olmaniz gerekmektedir')</script>");
				
				
				rd.include(request, response);
		}
			
		
		}catch (SQLException ex) {  
            message = "ERROR: " + ex.getMessage();  
            ex.printStackTrace();  
		}
		
			
		
		
		 
		
		
		
	}

}
