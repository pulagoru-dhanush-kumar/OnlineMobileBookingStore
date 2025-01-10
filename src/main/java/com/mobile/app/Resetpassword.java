package com.mobile.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Resetpassword extends HttpServlet {
	
	static int changepassword(String email,String pass,String conpass) throws ClassNotFoundException, SQLException, IOException
	{
		Connection connection=Jdbc.jdbcconnection();
		String query="update  users set password=? ,confirmpassword=? where email=?";
		PreparedStatement pst=connection.prepareStatement(query);
		pst.setString(1,pass);
		pst.setString(2, conpass);
		pst.setString(3, email);
		return pst.executeUpdate();
		
		
	}

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		 HttpSession session=req.getSession(); 
		 String email=(String)session.getAttribute("email");
		 System.out.println("email"+email);
		 email=email.toLowerCase();
		 if(email==null)
		 {
			 res.sendRedirect("forgot.html");
		 }
		 else
		 {
		 String pass=req.getParameter("password");
		 String conpass=req.getParameter("confirmPassword");
		 System.out.println(pass +" "+ conpass);
		 try {
			int y=changepassword(email,pass,conpass);
			if(y>0)res.sendRedirect("Dynamic.jsp");
			
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			
			e.printStackTrace();
		}
		 
		 }
		 
    }
}
