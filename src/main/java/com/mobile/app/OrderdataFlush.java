package com.mobile.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.catalina.connector.Response;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class OrderdataFlush extends HttpServlet {
	public static void sendOrderConfirmation(String recipientEmail, String mobileName, String transactionId, int totalPrice) {
	    // Sender's email credentials
	    String sender = "dhanushkumar6304030341@gmail.com"; 
	    String password = "sric ngxj wcrl hvqs";  

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
	        message.setSubject("Order Confirmation: Your Mobile Booking");

	        // Set the email content
	        String emailContent = "Dear Customer,\n\n"
	                + "Thank you for your purchase! Here are the details of your order:\n\n"
	                + "Mobile Name: " + mobileName + "\n"
	                + "Transaction ID: " + transactionId + "\n"
	                + "Total Price: ₹" + totalPrice + "\n\n"
	                + "Your order is confirmed and will be delivered to you shortly.\n\n"
	                + "Best Regards,\n"
	                + "Mobile Store Team";

	        message.setText(emailContent);

	        // Send the email
	        Transport.send(message);
	        System.out.println("Order confirmation email sent successfully!");
	    } catch (MessagingException e) {
	        e.printStackTrace();
	        System.out.println("Failed to send email: " + e.getMessage());
	    }
	}

	
	public static void Transactionotp(String em ,int otp) {
		
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
            message.setSubject("One time password for your Payment");
            message.setText("Otp  for your Payment is "+otp);
        
            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }
	
	
  public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException  
 
  {
	/*  phoneid INT,
	    email VARCHAR(50),
	    price INT,
	    Quantity INT,
	    totalPrice INT,*/
	  boolean b=SessionChecker.check(req, res);
	  if(b)
	  {
		  String address = req.getParameter("address");
		  String quant =req.getParameter("quantity");
		  String payment_method =req.getParameter("payment-method");
		  HttpSession ses=req.getSession(false);
		  String uname = (String) ses.getAttribute("uname");
          String pass = (String) ses.getAttribute("pass");
          String phoneid=(String)ses.getAttribute("phoneid");
          
          ses.setAttribute("quantity", quant);
          ses.setAttribute("address",address);
          ses.setAttribute("paymentmethod", payment_method);
		  System.out.println(uname+=" "+pass+" orderd phoneid"+phoneid+"sucessfully"+ "with address"+address+"and quantity is "+quant+" "
		  		+ "and the payment method is"+payment_method);
		  
		  
		  switch(payment_method)
		  {
		  case "phonepe":
			 
			  res.sendRedirect("Phonepay.jsp");
			  break;
		  case "googlepay":
			  res.sendRedirect("Googlepay.jsp");
			  break;
		  case "razorpay":
			  res.sendRedirect("Razoorpay.jsp");
			  break;
		default:
			res.sendRedirect("Dynamic.jsp");
		  }
	  }
	  else
	  {
		  res.sendRedirect("Signinrequired.html");
	  }
	 

  }
  static void dataflush(HttpServletRequest req,HttpServletResponse res,
		  int phoneid,String email,int Quantity,
		  int totalPrice,String status,
		  String paymentmethod , String transactionid,String address) throws ClassNotFoundException, SQLException, IOException
  {
	  Connection con=Jdbc.jdbcconnection();
	  String query="insert into orders (phoneid,email,Quantity,totalPrice,status,paymentmethod,transactionid,address)values(?,?,?,?,?,?,?,?)";
	  PreparedStatement pst=con.prepareStatement(query);
	  pst.setInt(1, phoneid);
	  pst.setString(2, email);
	  pst.setInt(3, Quantity);
	  pst.setInt(4, totalPrice);
	  pst.setString(5, status);
	  pst.setString(6, paymentmethod);
	  pst.setString(7, transactionid);
	  pst.setString(8, address);
	  int rows=pst.executeUpdate();
	  if(rows>0)
	  {
		  System.out.println("Sucessfully order placed");
		  res.sendRedirect("transactionsuccess.html");
		  String mobilename=Datafetcher.getmobilename(phoneid);
		  sendOrderConfirmation(email,mobilename,transactionid,totalPrice);
		  
	  }
	  else
	  {
		  System.out.println("error occurred");
		  res.sendRedirect("Googlepay.jsp");
	  }
	  
  }
}
