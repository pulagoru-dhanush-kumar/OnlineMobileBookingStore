package com.mobile.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginAuthentication extends HttpServlet {
	 static boolean logincheck(String email,String pass) throws SQLException, IOException, ClassNotFoundException {
	        Connection con=Jdbc.jdbcconnection();
	        String check="select * from users where email=?";
	        PreparedStatement pt= con.prepareStatement(check);
	        pt.setString(1,email);
	        ResultSet rs=pt.executeQuery();
	        while(rs.next())
	        {
	            if(rs.getString(1).equals(email) && rs.getString(2).equals(pass))
	            {
	                return true;
	            }
	        }
	        return false;
	    }
	 public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
		{
				String email=req.getParameter("email");
				String pass=req.getParameter("password");
				HttpSession session=req.getSession(true);
				email=email.toLowerCase();
				try {
					boolean message=logincheck(email,pass);
				if(message)
					{
					session.setAttribute("uname",email);
					session.setAttribute("pass", pass);
					System.out.println("i am login servlet "+email+ pass);
					res.sendRedirect("Dynamic.jsp");
					
					}
				else
				{
					PrintWriter out=res.getWriter();
					out.println("<b>invalid credentials</b>");
					res.sendRedirect("login.html");
				}
				} catch (ClassNotFoundException | SQLException | IOException e) {
					
					e.printStackTrace();
				}
		}


}
