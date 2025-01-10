package com.mobile.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class OtpVerification extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		String String=req.getParameter("otp");
		int userotp = Integer.parseInt(String); // Retrieve OTP provided by the user
        HttpSession session = req.getSession(); // Retrieve the current session
        int actualotp = session.getAttribute("otp")!=null ? (Integer) session.getAttribute("otp"):0; 
        String s=(java.lang.String) session.getAttribute("email");
        System.out.println(s);
        // Retrieve OTP stored in the session
       
        System.out.println("User OTP: " + userotp + ", Actual OTP: " + actualotp);

        // Validate OTP
        if (s!=null&&userotp ==actualotp) {
            res.sendRedirect("passwordform.html");
        } else {
        	
            res.sendRedirect("forgot.html");
        	
        }
}
}
