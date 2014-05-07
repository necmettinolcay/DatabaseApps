package com.necmettin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class submitServletmain
 */
@WebServlet("/submitServletmain")
public class submitServletmain extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message = (String) request.getAttribute("Message");
		String username = (String) request.getAttribute("User");
		String title = (String) request.getAttribute("Title");
		//String password = (String) request.getAttribute("Password");
		String password = (String) request.getSession().getAttribute("password");
		
		String countString = CookieUtility.getCookieValue(request, username , "1");
		
		int count = 1;
		try{
			count = Integer.parseInt(countString);
			
		}catch(NumberFormatException nfe){
			
		}
		LivedCookie c = new LivedCookie(username, String.valueOf(count+1));
		response.addCookie(c);
		
		
		
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel='stylesheet' href='./styles.css' type='text/css'/>");
		out.println("<title>" +title+ "</title>");
		out.println("</head>");
		out.println("<body>  ");
		out.println("<div align='center'>  ");
		out.println("<table style='width:50%;height:auto;' border='1'>");
		out.println("<thead> <td><font size='3' face='Arial' color='white' >Kullanýcý adý </font></td><td><font size='3' face='Arial' color='white' >Ünvan </font></td><td align='center'> <font size='3' face='Arial' color='white' >Resim  </font></td></thead>");
		out.println("<td> <font size='3' face='Arial' color='white' >" +username+ "</font></td>");
		out.println("<td><font size='3' face='Arial' color='white' >" +message+ "</font> </td>");
        out.println("<td style='width:150px; height:125px;'> <img src='" + "submitServlet?" + message + "' style='width:150px; height:125px;'/></td></tr>");
		out.println("</table>");
		out.println("<h4 style='background-color:white;'>Bu tarayýcýyý kullanarak " +
				+ count + ".kez bu sayfaya girdiniz</h4>");
		out.println("</div> ");
		out.println("</body>");
		out.println("</html>");
		
		
		
	}

}
