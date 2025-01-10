package com.mobile.app;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class PhonepayServlet
 */
public class PhonepayServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	HttpSession ses = request.getSession(false);
	    String uname = (String) ses.getAttribute("uname");
	    String phoneid = (String) ses.getAttribute("phoneid");
	    String quantity = (String) ses.getAttribute("quantity");
	    int gpayotp= (int) ses.getAttribute("phonepayotp");
	    String otp=request.getParameter("otp");
	    String Tid=request.getParameter("transactionId");
	    String address=(String)ses.getAttribute("address");
	    if(uname==null)
		{
			response.sendRedirect("Signinrequired.html");
		}
		 int totalPrice=0;
		if(phoneid!=null && quantity!=null && Integer.parseInt(otp)==gpayotp){
	    int pi = Integer.parseInt(phoneid);
	    int qn = Integer.parseInt(quantity);
	 	try {
			totalPrice = com.mobile.app.Datafetcher.totalPrice(pi, qn);
		} catch (ClassNotFoundException | SQLException | IOException e) {
		
			e.printStackTrace();
		}
	 	String paymentmethod=(String)ses.getAttribute("paymentmethod");
	 	System.out.println(uname+"wants to order phone id");
	 	System.out.println("   phone id is:"+phoneid);
	 	System.out.println("   quantity:"+quantity);
	 	System.out.println("   totalprice:"+totalPrice);
	 	System.out.println("   transaction id is"+Tid+" your otp is"+otp);
	 	System.out.println("    Adress is"+ address);
	 	System.out.println(" Paymentmethod is"+paymentmethod);
	 	
	 try {
		OrderdataFlush.dataflush(request, response, pi, uname,  Integer.parseInt(quantity),totalPrice,"Shipped",paymentmethod,Tid,address);
	} catch (NumberFormatException | ClassNotFoundException | SQLException | IOException e) {
		
		e.printStackTrace();
	}		 	
	 	
	 	
}
       
	} 

}
