package com.mobile.app;

import java.io.IOException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		HttpSession session=req.getSession(false);
		if(session!=null) {
		session.removeAttribute("uname");
		session.removeAttribute("pass");
		}
		res.sendRedirect("Dynamic.jsp");
		
	}

}
