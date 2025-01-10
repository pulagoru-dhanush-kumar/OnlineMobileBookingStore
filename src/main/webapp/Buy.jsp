<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.mobile.app.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
HttpSession  ses=request.getSession(false);

String uname=(String)ses.getAttribute("uname");
String pass=(String)ses.getAttribute("pass");

String phoneid=request.getParameter("phoneid");
HttpSession s2=request.getSession();
s2.setAttribute("phoneid",phoneid);

int pid=Integer.parseInt(phoneid);

String[] phone = PhoneServlet.phonedata(pid);
String[] arr = phone[2].split("}");


if(pass!=null && uname!=null)
{
	System.out.println(phoneid);
	System.out.println(uname+" "+pass);
	String unames=(String)ses.getAttribute("uname");
	String passs=(String)ses.getAttribute("pass");
	
	System.out.println(unames+" "+passs);
	
	response.sendRedirect("Buynow.html");
}
else
{	
	response.sendRedirect("registration.html");
}
%>
</body>
</html>



























