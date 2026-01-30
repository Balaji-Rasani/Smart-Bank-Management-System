<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <style>
        /* Full page background */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: url('${pageContext.request.contextPath}/resources/images/bank.png') no-repeat center center fixed;
            background-size: cover;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        /* Top logo */
        .top-logo {
            width: 120px;
            margin: 30px 0 20px 0;
        }

        /* Welcome message with emojis */
        h2 {
            font-size: 36px;
            font-weight: bold;
            margin-bottom: 40px;
            color: #000000; /* changed to black */
            text-shadow: 2px 2px 5px rgba(0,0,0,0.5);
        }

        h2 span.emoji {
            margin: 0 10px;
        }

        /* Card container */
        .card-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
            max-width: 1000px;
            margin-bottom: 50px;
        }

        /* Individual card */
        .card {
            background-color: rgba(255, 255, 255, 0.95);
            width: 220px;
            padding: 20px;
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.2);
            text-align: center;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 20px rgba(0,0,0,0.3);
        }

        .card a {
            display: block;
            font-size: 18px;
            font-weight: bold;
            color: #007BFF;
            margin-bottom: 8px;
            text-decoration: none;
        }

        .card a:hover {
            text-decoration: underline;
        }

        .card small {
            display: block;
            font-size: 14px;
            color: #555;
        }

        /* Admin login button */
        .admin-login-btn {
            display: inline-block;
            margin-bottom: 40px;
            padding: 12px 30px;
            background-color: #007BFF; /* blue */
            color: #fff;
            font-size: 16px;
            font-weight: bold;
            border-radius: 5px;
            text-decoration: none;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }

        .admin-login-btn:hover {
            background-color: #0056b3;
            transform: translateY(-3px);
        }
    </style>
</head>
<body>

    <!-- Top logo image -->
    <img src="${pageContext.request.contextPath}/resources/images/admin panel.png" alt="Bank Logo" class="top-logo">

    <!-- Welcome message with emojis -->
    <h2>👋 Welcome <span class="emoji"> 🧑‍💼</span> To Admin Dashboard <span class="emoji">🏦</span> 👋</h2>

    <div class="card-container">
        <div class="card">
            <a href="createAccount">Create Account</a>
            <small>Open a new Customer account</small>
        </div>
        <div class="card">
            <a href="manageLoans">Manage Loans</a>
            <small>View & Update Loan Applications</small>
        </div>
        <div class="card">
            <a href="manageCustomers">View Customers</a>
            <small>Check Customer Details & Accounts</small>
        </div>
        <div class="card">
            <a href="reports">Generate Reports</a>
            <small>View detailed reports</small>
        </div>
    </div>

    <!-- Go to Admin Login Button -->
    <a href="${pageContext.request.contextPath}/admin/login" class="admin-login-btn">Go to Admin Login</a>

</body>
</html>
