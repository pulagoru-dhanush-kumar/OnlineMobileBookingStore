package com.mobile.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class Authentication extends HttpServlet {
	
	
	 public static void sendconfirmation(String em, String body ) {
	        // Recipient's email ID
	        String recipient = em; 

	        // Sender's email credentials
	        String sender = "dhanushkumar6304030341@gmail.com"; // Replace with your email
	        String password = "sric ngxj wcrl hvqs";  // Use the generated App Password here

	        // SMTP server configuration
	        Properties props = new Properties();
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.debug", "true");  // Enable debugging

	        // Create a session with authentication
	        Session session = Session.getInstance(props, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(sender, password);
	            }
	        });

	        try {
	            // Create a MimeMessage object
	            Message message = new MimeMessage(session);

	            // Set the email details
	            message.setFrom(new InternetAddress(sender));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
	            message.setSubject("Thanks You for registering into Online mobile booking store ");
	            message.setText(body);
	            // Send the email
	            Transport.send(message);
	            System.out.println("Email sent successfully!");
	        } catch (MessagingException e) {
	            e.printStackTrace();
	            System.out.println("Failed to send email: " + e.getMessage());
	        }
	 }
	 
	static String registration(String email,String pass,String conpass) throws ClassNotFoundException, SQLException, IOException {
        Connection con = Jdbc.jdbcconnection();
        email=email.toLowerCase();
        String check = "select * from users where email=?";
        PreparedStatement pt = con.prepareStatement(check);
        pt.setString(1, email);
        ResultSet rs = pt.executeQuery();
        boolean b = false;
        while (rs.next()) {
            if (rs.getString(1).equals(email)) {
                b = true;
            }
        }
        if (b) {
        	
            return "User already exists";
        } 
        
        else {
            if (email != null && pass.equals(conpass)) {
                String s = "insert into users values(?,?,?)";
                pt = con.prepareStatement(s);
                pt.setString(1, email);
                pt.setString(2, pass);
                pt.setString(3, conpass);
                int i = pt.executeUpdate();
                if (i > 0) {
                	
                Authentication.sendconfirmation(email,"Welcome! Your account has been successfully created. Start exploring our mobile application for the latest products and exciting offers!");
                return "registered sucessfully";
                } 
                
            } 
            else {
               return "Incorrect passwords";
            }

        }
		return "error at Authentication.java servlet ";
    }
	
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
			String email=req.getParameter("email");
			String pass=req.getParameter("password");
			String conpass=req.getParameter("confirmpassword");
			HttpSession session=req.getSession(true);
			session.setAttribute("uname",email);
			session.setAttribute("pass", pass);
			
			try {
				String message=registration(email,pass,conpass);
				if(message.equals("registered sucessfully"))
					{
						
					res.sendRedirect("sucessfuLogin.html");
					}
				else 
				{
					PrintWriter out=res.getWriter();
					out.println(message);
					res.sendRedirect("registration.html");
				}
			} catch (ClassNotFoundException | SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

}
