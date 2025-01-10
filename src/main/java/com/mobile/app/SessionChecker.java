package com.mobile.app;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SessionChecker extends HttpServlet {
    
   
    public static boolean check(HttpServletRequest request, HttpServletResponse response) {
        HttpSession ses = request.getSession(false);
        
      
        if (ses != null) {
            String uname = (String) ses.getAttribute("uname");
            String pass = (String) ses.getAttribute("pass");
            
            if (uname != null && pass != null) {
                
                String phoneid = request.getParameter("phoneid");
                
                if (phoneid != null) {
                    ses.setAttribute(phoneid, phoneid);
                    int pid = Integer.parseInt(phoneid);
                    System.out.println("Phone ID: " + phoneid);
                    System.out.println("Username: " + uname + " Password: " + pass);
                }
                
               
                return true;
            }
        }
        
       
        return false;
    }
}
