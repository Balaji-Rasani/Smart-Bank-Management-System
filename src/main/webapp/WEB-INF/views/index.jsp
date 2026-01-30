<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>Bank Management System - Home</title>
    <style>
        /* Body with background image */
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
            min-height: 100vh;
            background: url('${pageContext.request.contextPath}/resources/images/bank.png') no-repeat center center fixed;
            background-size: cover;
            position: relative;
        }

        /* Optional dark overlay for readability */
        body::before {
            content: "";
            position: absolute;
            top: 0; left: 0;
            width: 100%; height: 100%;
            background: rgba(0, 0, 0, 0.35);
            z-index: -1;
        }

        /* Container with border and transparency */
        .container {
            display: inline-block;
            border: 3px solid black;
            border-radius: 15px;
            padding: 40px 60px;
            background-color: rgba(255, 255, 255, 0.7);
            box-shadow: 0 4px 15px rgba(0,0,0,0.2);
            transition: transform 0.3s ease;
        }

        .container:hover {
            transform: scale(1.02);
        }

        /* Marquee inside container (thick professional blue) */
        .marquee-box {
            width: 100%;
            overflow: hidden;
            border: 1px solid #e74c3c;
            border-radius: 10px;
            margin-bottom: 20px;
            background-color: rgba(255,255,255,0.5);
        }

        .marquee-text {
            display: inline-block;
            white-space: nowrap;
            animation: scrollText 10s linear infinite;
            font-size: 24px;           /* slightly bigger */
            font-weight: 900;          /* thick */
            color: #1E3A8A;            /* deep blue */
            text-shadow: 1px 1px 2px #60A5FA; /* subtle shadow */
            padding: 5px 0;
        }

        @keyframes scrollText {
            0% { transform: translateX(100%); }
            100% { transform: translateX(-100%); }
        }

        /* Heading with gradient */
        h1 {
            font-size: 40px;
            font-weight: 900; /* thick */
            background: linear-gradient(90deg, #1E3A8A, #5B21B6, #F59E0B);
            background-size: 200% auto;
            color: transparent;
            background-clip: text;
            -webkit-background-clip: text;
            text-shadow: 2px 2px 6px rgba(0,0,0,0.3);
            animation: gradientFlow 4s ease-in-out infinite;
            margin-bottom: 30px;
        }

        h1 span {
            color: #F59E0B; /* highlight Management in orange */
        }

        @keyframes gradientFlow {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        /* Role selection message */
        .role-message {
            font-size: 20px;
            font-weight: bold;
            color: #34495e;
            margin-bottom: 20px;
        }

        /* Radio button styles */
        .role-option {
            font-size: 18px;
            margin: 10px 20px;
            cursor: pointer;
        }

        /* Button style */
        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin: 20px;
            font-size: 16px;
            background-color: #3498db;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            box-shadow: 2px 2px 6px rgba(0,0,0,0.1);
            transition: 0.3s;
        }

        .btn:hover {
            background-color: #2980b9;
            transform: scale(1.05);
        }
    </style>
</head>
<body>
    <!-- Bordered container -->
    <div class="container">

        <!-- Marquee inside the container -->
        <div class="marquee-box">
            <div class="marquee-text">
                🚀 Secure Banking 💳 | Fast Transactions ⚡ | Your Money, Your Control! 💰
            </div>
        </div>

        <!-- Heading message -->
        <h1>
            Welcome to  Smart Bank <span>Management</span> System
        </h1>

        <!-- Role selection message -->
        <div class="role-message">Please select your role:</div>

        <!-- Radio buttons for role selection -->
        <div>
            <label class="role-option">
                <input type="radio" name="role" value="customer" onclick="redirectToLogin(this.value)">
                <strong>Customer</strong>
            </label>
            <label class="role-option">
                <input type="radio" name="role" value="admin" onclick="redirectToLogin(this.value)">
                <strong>Admin</strong>
            </label>
        </div>
    </div>

    <script>
        function redirectToLogin(role) {
            if(role === 'customer') {
                window.location.href = '${pageContext.request.contextPath}/customer/login';
            } else if(role === 'admin') {
                window.location.href = '${pageContext.request.contextPath}/admin/login';
            }
        }
    </script>
</body>
</html>
