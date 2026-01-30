<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
    <style>
        /* Body style with background */
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            background: url('${pageContext.request.contextPath}/resources/images/bank.png') no-repeat center center fixed;
            background-size: cover;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        /* Optional overlay for readability */
        body::before {
            content: "";
            position: absolute;
            top: 0; left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0,0,0,0.3);
            z-index: -1;
        }

        /* Login container */
        .login-container {
            background: linear-gradient(to bottom, #E0F2FF, #FFFFFF);
            border: 3px solid #1E3A8A;
            border-radius: 15px;
            padding: 30px 50px;
            box-shadow: 0 5px 20px rgba(0,0,0,0.3);
            text-align: center;
            max-width: 400px;
            width: 100%;
            position: relative;
        }

        /* Top banner image */
        .login-container img {
            width: 100px;
            margin-bottom: 20px;
        }

        h2 {
            color: #1E3A8A;
            font-weight: 900;
            margin-bottom: 20px;
        }

        /* Form inputs */
        input[type="text"], input[type="password"] {
            width: 90%;
            padding: 10px;
            margin: 10px 0;
            border-radius: 8px;
            border: 1px solid #3498db;
            font-size: 16px;
        }

        /* Button style */
        button {
            width: 95%;
            padding: 10px;
            background-color: #1E3A8A;
            color: white;
            font-size: 16px;
            font-weight: bold;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            margin-top: 10px;
            transition: 0.3s;
        }

        button:hover {
            background-color: #3B82F6;
        }

        /* Links */
        a {
            text-decoration: none;
            color: #1E3A8A;
            font-weight: bold;
            margin: 0 5px;
        }

        a:hover {
            color: #3B82F6;
        }

        /* Messages */
        .error {
            color: red;
            margin-bottom: 10px;
            font-weight: bold;
        }

        .message {
            color: green;
            margin-bottom: 10px;
            font-weight: bold;
        }

        /* Home button */
        .home-button {
            margin-top: 20px;
        }

        .home-button a {
            display: inline-block;
            padding: 10px 20px;
            background-color: #10B981; /* green */
            color: white;
            font-weight: bold;
            border-radius: 8px;
            text-decoration: none;
            transition: 0.3s;
        }

        .home-button a:hover {
            background-color: #059669; /* darker green */
        }
    </style>
</head>
<body>
    <div class="login-container">
        <!-- Top banner image -->
        <img src="${pageContext.request.contextPath}/resources/images/login.png" alt="Bank Logo">

        <h2>Customer Login</h2>

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <c:if test="${not empty message}">
            <div class="message">${message}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/customer/login" method="post">
            <input type="text" name="accountNo" placeholder="Account No" required><br>
            <input type="password" name="password" placeholder="Password" required><br>
            <button type="submit">Login</button>
        </form>

        <div style="margin-top: 15px;">
            <a href="${pageContext.request.contextPath}/customer/register">Register</a> |
            <a href="${pageContext.request.contextPath}/customer/resetpassword">Forgot Password?</a>
        </div>

        <!-- Home button -->
        <div class="home-button">
            <a href="${pageContext.request.contextPath}/index">Go to Home</a>
        </div>
    </div>
</body>
</html>
