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

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Customersupport extends HttpServlet {
	public static void sendOrderConfirmation(String recipientEmail, String messages,String subjects) {
	    // Sender's email credentials
	    String sender = "dhanushkumar6304030341@gmail.com"; // Replace with your email
	    String password = "sric ngxj wcrl hvqs";  // Use the generated App Password here

	    // SMTP server configuration
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");

	    // Create a session with authentication
	    Session session = Session.getInstance(props, new Authenticator() {
	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(sender, password);
	        }
	    });

	    try {
	        // Create a MimeMessage object
	        Message message = new MimeMessage(session);

	        // Set the email details
	        message.setFrom(new InternetAddress(sender));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
	        message.setSubject("Resolving issue");

	        // Set the email content
	        String emailContent = "Dear Customer,\n\n"
                    + "We have received your issue and our team is working on resolving it. "
                    + "Please rest assured that we will get back to you shortly with a solution.\n\n"
                    + "Subject: " + subjects + "\n"
                    + "Message: " + messages + "\n\n"
                    + "Thank you for your patience.\n\n"
                    + "Best Regards,\n"
                    + "Mobile Store Support Team\n\n"
                    + "This is a system-generated email. Please do not reply to this message.";



	        message.setText(emailContent);

	        // Send the email
	        Transport.send(message);
	        System.out.println("issue email sent successfully!");
	    } catch (MessagingException e) {
	        e.printStackTrace();
	        System.out.println("Failed to send email: " + e.getMessage());
	    }
	}

	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        
	        String name = request.getParameter("name");
	        String email = request.getParameter("email");
	        String subject = request.getParameter("subject");
	        String message = request.getParameter("message");
	        try {
				Connection con=Jdbc.jdbcconnection();
				String query="insert into customersupport values(?,?,?)";
				PreparedStatement pst=con.prepareStatement(query);
				pst.setString(1, email);
				pst.setString(2, message);
				pst.setString(3, "No");
				pst.executeUpdate();
				response.sendRedirect("customeremailsuccess.html");
				sendOrderConfirmation(email,message,subject);
				
				
				
			} catch (ClassNotFoundException | SQLException | IOException e) {
				response.sendRedirect("Dynamic.jsp");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
	        
	        }
}
