
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.mobile.app.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Flipkart Payment Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
            color: #333;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        }
        h3 {
            text-align: center;
            color: #444;
        }
        p {
            font-size: 18px;
            text-align: center;
            margin-bottom: 20px;
        }
        .payment-info {
            text-align: center;
        }
        .payment-info img {
            width: 200px;
            height: 200px;
            margin: 20px 0;
            border: 2px solid #ddd;
            border-radius: 8px;
        }
        .transaction {
            margin: 20px 0;
            text-align: center;
        }
        .transaction label {
            font-size: 16px;
            margin-bottom: 8px;
            display: block;
        }
        .transaction input {
            width: 80%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .submit-btn {
            text-align: center;
            margin-top: 20px;
        }
        .submit-btn button {
            background-color: #28a745;
            color: white;
            font-size: 18px;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .submit-btn button:hover {
            background-color: #218838;
        }
        @media (max-width: 600px) {
            .container {
                margin: 20px;
                padding: 15px;
            }
            .transaction input {
                width: 90%;
            }
        }
    </style>
</head>
<body>
 <%
 HttpSession ses = request.getSession(false);
 String uname = (String) ses.getAttribute("uname");
 String phoneid = (String) ses.getAttribute("phoneid");
 String quantity = (String) ses.getAttribute("quantity");

 if(uname==null)
	{
		response.sendRedirect("Signinrequired.html");
	}
	  int totalPrice=0;
	if(phoneid!=null && quantity!=null){
 int pi = Integer.parseInt(phoneid);
 int qn = Integer.parseInt(quantity);
	totalPrice = com.mobile.app.Datafetcher.totalPrice(pi, qn);
 int otp = (int) (Math.random() * 900000) + 100000; 
 ses.setAttribute("phonepayotp", otp);
 System.out.println("Generated OTP: " + otp);
 OrderdataFlush.Transactionotp(uname, otp);
	}
   
%>
    <div class="container">
        <h3>Total Amount through GooglePay</h3>
        <p>Your Total price is: ₹<%= totalPrice %></p>

        <div class="payment-info">
            <h4>Scan the QR Code below to proceed with your payment:</h4>
            <img src="C:/Users/dhanushkumar/eclipse-workspace/OnlineMobileBookingStore/Mobiles/Images/phonepay.jpeg" alt="Google Pay QR Code" class="qr-code">
        </div>

        <form action="phonepay" method="post">
        
        
                    <div class="transaction">
                <label for="otp">Enter OTP</label>
                <input type="text" id="otp" name="otp" placeholder="Enter the OTP sent to your email/SMS" required>
            </div>
            <div class="transaction">
                <label for="transaction-id">Transaction ID</label>
                <input type="text" id="transaction-id" name="transactionId" placeholder="Enter your transaction ID" required>
            </div>

            <div class="submit-btn">
                <button type="submit" name="confirm">Place Order</button>
            </div>
        </form>
    </div>
</body>
</html>
