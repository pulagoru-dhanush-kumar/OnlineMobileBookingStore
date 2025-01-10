<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobile.app.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mobile Store</title>
   <style>
    body {
        margin: 0;
        font-family: Arial, sans-serif;
        background-color: #f1f3f6;
    }

    .navbar {
        background-color: #2874f0;
        padding: 10px 20px;
        color: white;
    }

    .container {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .logo {
        font-size: 20px;
        font-weight: bold;
        text-decoration: none;
        color: white;
    }

    .nav-links {
        list-style: none;
        display: flex;
        margin: 0;
        padding: 0;
    }

    .nav-links li {
        margin: 0 15px;
    }

    .nav-links a {
        text-decoration: none;
        color: white;
        font-size: 16px;
        transition: color 0.3s;
    }

    .nav-links a:hover {
        color: #ffeb3b;
    }

    .product-list {
        padding: 20px;
    }

    .product-card {
        display: flex;
        background-color: white;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        overflow: hidden;
        margin-bottom: 20px;
        transition: box-shadow 0.3s;
    }

    .product-card:hover {
        box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3);
    }

    .product-card img {
        width: 150px;
        height: 150px;
        object-fit: contain;
    }

    .product-info {
        padding: 15px;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        width: 100%;
    }

    .product-title {
        font-size: 18px;
        font-weight: bold;
        margin: 5px 0;
        color: #333;
    }

    .product-price {
        font-size: 16px;
        color: #2874f0;
        margin: 5px 0;
    }

    .product-offer {
        font-size: 14px;
        color: #388e3c;
        margin: 5px 0;
    }

    .buy-now {
        display: inline-block;
        background-color: #ff5722;
        color: white;
        padding: 8px 8px;
        text-decoration: none;
        border-radius: 5px;
        font-size: 14px;
        font-weight: bold;
        margin-top: 5px;
        transition: background-color 0.3s;
        width: 80px;
    }

    .buy-now:hover {
        background-color: #e64a19;
    }

    .product-specs {
        font-size: 14px;
        color: #555;
        margin: 5px 0;
        line-height: 1.5;
    }

    /* New styles for features with bullet points */
    .product-specs ul {
        padding-left: 20px;
        margin: 5px 0;
    }

    .product-specs ul li {
        font-size: 14px;
        color: #666;
        margin: 1px 0;
        padding-left: 5px;
    }

    .logout-message {
        background-color: #f44336;
        color: white;
        padding: 10px;
        text-align: center;
        display: none; /* Initially hidden */
    }
</style>
   
</head>
<body>

<nav class="navbar">
    <div class="container">
        <a href="#" class="logo">Dhanush Mobile Booking Store</a>
        <ul class="nav-links">
            <li><a href="Dynamic.jsp"><b>Home</b></a></li>
            <li><a href="MyOrders.jsp"><b>MyOrders</b></a></li>
            <li><a href="Contact.html"><b>Contact</b></a></li>
            <li><a href="about.html"><b>About</b></a></li>
            <li><a href="registration.html"><b>Register / Login</b></a></li>
            <li><a href="logout" id="logoutBtn"><b>Logout</b></a></li>
        </ul>
    </div>
</nav>

<!-- Logout success message -->
<div id="logoutMessage" class="logout-message">
    Logged out successfully.
</div>

<%
for (int i = 1; i < 13; i++) {
    // Call the phonedata method and get the phone data
    String[] phone = PhoneServlet.phonedata(i);
    String[] arr = phone[2].split("}");
%>

    <div class="product-list">
        <div class="product-card">
            <img src="<%= phone[3] %>" alt="mobileimage">
            <div class="product-info">
                <div class="product-title"><%= phone[0] %></div>
                <div class="product-price">₹<%= phone[1] %></div>
                <div class="product-offer"><%= arr[0] %></div>
                <div class="product-specs">
                    <strong>Highlights:</strong>
                    <ul>
                        <li><%= arr[1] %></li>
                        <li><%= arr[2] %></li>
                        <li><%= arr[3] %></li>
                    </ul>
                </div>
                <form action="Buy.jsp" method="post">
                    <input type="hidden" name="phoneid" value="<%= i %>">
                    <button type="submit" class="buy-now">Buy Now</button>
                </form>
            </div>
        </div>
    </div>

<% } %>
<script>
    document.getElementById('logoutBtn').addEventListener('click', function() {
        document.getElementById('logoutMessage').style.display = 'block';
        
        setTimeout(function() {
            document.getElementById('logoutMessage').style.display = 'none';
        }, 3000);
    });
</script>

</body>  
</html>
