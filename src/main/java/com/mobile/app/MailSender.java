package com.mobile.app;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import jakarta.servlet.http.HttpServlet;

public class MailSender extends HttpServlet{
    public static void sendotp(String em,int otp) {
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
            message.setSubject("One time password for Mobile booking store app");
            message.setText("otp :"+otp+"  "+"This is otp for changing your password Don't share to anyone");
        
            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }
}
