package com.mobile.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Forgotpassword extends HttpServlet{
	  
	
	public static void forgopass(HttpServletRequest req,HttpServletResponse res) throws IOException, ClassNotFoundException, SQLException
	{
		String email=req.getParameter("email");
		System.out.println(email);
		Connection con=Jdbc.jdbcconnection();
		String query = "SELECT * FROM users WHERE email = ?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, email);

		ResultSet rs = pst.executeQuery();
		rs.next();
		String em=rs.getString(1);
		if(em.equals(email) && em!=null&& email!=null)
		{
			int otp = (int) (Math.random() * 900000) + 100000;
			System.out.println(otp);
			com.mobile.app.MailSender.sendotp(email,otp);
			String rotp=req.getParameter("otp");
			System.out.println("otp is correct");
			HttpSession session=req.getSession();
			session.setAttribute("email",email);
			session.setAttribute("otp", otp);
			res.sendRedirect("onetimepassword.html");
		}
		else
		{
			res.sendRedirect("registration.html");
		}
				
		
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		
		try {
			forgopass(req,res);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
}
