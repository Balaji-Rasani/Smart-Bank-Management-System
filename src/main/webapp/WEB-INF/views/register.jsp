<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Register</title>
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

        /* Registration container */
        .register-container {
            background: linear-gradient(to bottom, #FFFFFF, #E0F2FF); /* light inside gradient */
            border: 3px solid black;  /* black border */
            border-radius: 15px;
            padding: 30px 50px;
            box-shadow: 0 5px 20px rgba(0,0,0,0.3);
            text-align: center;
            max-width: 450px;
            width: 100%;
        }

        /* Top banner image */
        .register-container img {
            width: 100px;
            margin-bottom: 20px;
        }

        h2 {
            color: #1E3A8A;
            font-weight: 900;
            margin-bottom: 25px;
        }

        /* Form inputs */
        input[type="text"], input[type="email"], input[type="password"] {
            width: 90%;
            padding: 10px;
            margin: 10px 0;
            border-radius: 8px;
            border: 1px solid #000000;
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
            margin-top: 15px;
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
    </style>
</head>
<body>
    <div class="register-container">
        <!-- Top banner image -->
        <img src="${pageContext.request.contextPath}/resources/images/register.png" alt="Bank Logo">

        <h2>New Customer Registration</h2>
        <form action="register" method="post">
            <input type="text" name="accountNo" placeholder="Account No" required><br>
            <input type="email" name="email" placeholder="Email" required><br>
            <input type="password" name="password" placeholder="Password" required><br>
            <button type="submit">Register</button>
        </form>

        <div style="margin-top: 15px;">
            <a href="${pageContext.request.contextPath}/customer/login">Back to Login</a>
        </div>
    </div>
</body>
</html>
