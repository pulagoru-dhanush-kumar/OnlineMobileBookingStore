<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Orders</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f9;
        margin: 0;
        padding: 0;
    }
    .container {
        max-width: 800px;
        margin: 20px auto;
        padding: 20px;
        background-color: #fff;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
    }
    .order {
        display: flex;
        align-items: center;
        border: 1px solid #ddd;
        border-radius: 8px;
        margin-bottom: 20px;
        padding: 15px;
        background-color: #fafafa;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        transition: transform 0.2s;
    }
    .order:hover {
        transform: scale(1.02);
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    }
    .order img {
        width: 120px;
        height: 120px;
        object-fit: cover;
        border-radius: 8px;
        margin-right: 15px;
    }
    .order-details {
        flex: 1;
    }
    .order-details h3 {
        margin: 0 0 10px;
        font-size: 20px;
        color: #333;
    }
    .order-details p {
        margin: 5px 0;
        font-size: 14px;
        color: #555;
    }
    .order-details ul {
        padding-left: 20px;
        margin: 5px 0;
    }
    .order-details ul li {
        font-size: 14px;
        color: #666;
    }
</style>
</head>
<body>
<%
HttpSession mysession = request.getSession(false);
String uname = (String) mysession.getAttribute("uname");
String pass = (String) mysession.getAttribute("pass");

if (pass != null && uname != null) {
    ArrayList<String[]> arr = com.mobile.app.Myorders.getorders(uname);
    if (arr.size() > 0) {
%>
<div class="container">
    <h1>My Orders</h1>
    <% for (int i = 0; i < arr.size(); i++) {
        String item[] = arr.get(i);
        String name = item[1];
        String totalprice = item[2];
        String quantity = item[3];
        String status = item[4];
        String features[] = item[5].split("}");
        String imgsrc = item[6];
        String time = item[7];
    %>
    <div class="order">
        <img src="<%= imgsrc %>" alt="<%= name %>">
        <div class="order-details">
            <h3><%= name %></h3>
            <p><strong>Total Price:</strong> ₹<%= totalprice %></p>
            <p><strong>Quantity:</strong> <%= quantity %></p>
            <p><strong>Status:</strong> <%= status %></p>
            <p><strong>Features:</strong>
                <ul>
                    <% for (String feature : features) { %>
                    <li><%= feature %></li>
                    <% } %>
                </ul>
            </p>
            <p><strong>Order Time:</strong> <%= time %></p>
        </div>
    </div>
    <% } %>
</div>
<%
    } else {
        response.sendRedirect("Noorders.html");
    }
} else {
    response.sendRedirect("registration.html");
}
%>
</body>
</html>
