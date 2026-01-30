<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Customer Dashboard</title>
    <style>
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            background: url('${pageContext.request.contextPath}/resources/images/bank.png') no-repeat center center fixed;
            background-size: cover;
            margin: 0;
            padding: 0;
            color: #333;
            text-align: center;
        }

        /* Logo styling */
        .logo {
            margin: 30px 0;
        }

        .logo img {
            height: 80px;
        }

        h1 {
            margin: 20px 0;
        }

        h2 {
            margin: 30px 0 20px 0;
        }

        /* Card container */
        .card-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
            padding: 0 20px;
        }

        /* Card styling */
        .card {
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
            width: 200px;
            padding: 20px;
            transition: transform 0.2s, box-shadow 0.2s;
        }

        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 16px rgba(0,0,0,0.3);
        }

        .card h3 {
            margin-bottom: 15px;
            font-size: 18px;
        }

        .card a {
            display: inline-block;
            padding: 8px 12px;
            background-color: #007bff;
            color: white;
            border-radius: 5px;
            text-decoration: none;
            transition: background-color 0.2s;
        }

        .card a:hover {
            background-color: #0056b3;
        }

        .back-login {
            display: block;
            margin: 40px 0;
            font-size: 16px;
        }

        hr {
            margin: 40px auto;
            border: none;
            border-top: 1px solid #ccc;
            width: 60%;
        }
    </style>
</head>
<body>

    <!-- Top Logo/Image -->
    <div class="logo">
        <img src="${pageContext.request.contextPath}/resources/images/banklogo.png" alt="Bank Logo"/>
    </div>

    <h1>Welcome! Your Account Number is: ${sessionScope.accountNo}</h1>  

    <!-- Account Dashboard Section -->
    <h2>Account Dashboard</h2>
    <div class="card-container">
        <div class="card">
            <h3>View Balance</h3>
            <a href="${pageContext.request.contextPath}/customer/viewBalance">Check Balance</a>
        </div>
        <div class="card">
            <h3>Transactions</h3>
            <a href="${pageContext.request.contextPath}/customer/transactions">View Transactions</a>
        </div>
        <div class="card">
            <h3>Loan Status</h3>
            <a href="${pageContext.request.contextPath}/customer/loan/status">Check Status</a>
        </div>
    </div>

    <hr/>

    <!-- Customer Services Section -->
    <h2>Customer Services</h2>
    <div class="card-container">
        <div class="card">
            <h3>Fund Transfer</h3>
            <a href="${pageContext.request.contextPath}/customer/fundTransfer">Transfer Funds</a>
        </div>
        <div class="card">
            <h3>Mini Statement</h3>
            <a href="${pageContext.request.contextPath}/customer/miniStatement">View Statement</a>
        </div>
        <div class="card">
            <h3>Loan Application</h3>
            <a href="${pageContext.request.contextPath}/customer/loanApplication">Apply for Loan</a>
        </div>
    </div>

   <!-- Back to Login Button -->
<div class="back-login">
    <a href="${pageContext.request.contextPath}/customer/login"
       style="display: inline-block;
              background-color: red;
              color: white;
              font-weight: bold;
              text-decoration: none;
              padding: 10px 20px;
              border-radius: 6px;
              transition: 0.3s;">
       Back to Login
    </a>
</div>


</body>
</html>
