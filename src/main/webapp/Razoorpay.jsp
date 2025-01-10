<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment Options</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: black;
        color: #333;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    .container {
        text-align: center;
        background: #fff;
        padding: 30px;
        border-radius: 8px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        max-width: 500px;
        width: 90%;
    }
    h2 {
        color: #ff4d4d;
        margin-bottom: 20px;
    }
    p {
        font-size: 18px;
        margin-bottom: 20px;
        color: #555;
    }
    .payment-options {
        margin: 20px 0;
    }
    .payment-options a {
        display: inline-block;
        text-decoration: none;
        background-color: #28a745;
        color: #fff;
        padding: 10px 20px;
        margin: 10px;
        border-radius: 4px;
        font-size: 16px;
        transition: background-color 0.3s ease;
    }
    .payment-options a:hover {
        background-color: #218838;
    }
  
</style>
</head>
<body>
    <div class="container">
        <h2>Razorpay Transactions Are Currently on Hold</h2>
        <p>We apologize for the inconvenience. You can still complete your transaction using the following methods:</p>
     

        <p>Scan the QR code above or use the buttons below:</p>
        <div class="payment-options">
            <a href="Googlepay.jsp">Pay with Google Pay</a>
            <a href="Phonepay.jsp">Pay with PhonePe</a>
        </div>
    </div>
</body>
</html>
