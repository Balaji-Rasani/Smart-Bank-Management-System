<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Admin Login - Bank Management System</title>
    <style>
        /* Full page background */
        body { 
            font-family: Arial, sans-serif; 
            background: url('${pageContext.request.contextPath}/resources/images/bank.png') no-repeat center center fixed;
            background-size: cover;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .login-container {
            width: 400px; 
            padding: 35px; 
            background-color: rgba(255, 255, 255, 0.95);
            box-shadow: 0 0 15px rgba(0,0,0,0.3); 
            border-radius: 10px;
            border: 2px solid black;
            text-align: center;
            position: relative;
        }

        /* Top logo */
        .login-container img.top-logo {
            width: 120px;
            margin-bottom: 20px;
        }

        h2 {
            font-size: 26px;
            font-weight: bold;
            margin-bottom: 25px;
            color: #333;
        }

        label {
            display: block;
            text-align: left;
            font-size: 16px;
            font-weight: bold;
            margin-top: 12px;
            color: #333;
        }

        input[type=text], input[type=password] {
            width: 100%; 
            padding: 12px; 
            margin-top: 6px;
            box-sizing: border-box;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 16px;
        }

        .password-container {
            position: relative;
        }

        .toggle-password {
            position: absolute;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
            cursor: pointer;
            font-size: 16px;
            color: #666;
        }

        input[type=submit] {
            width: 100%; 
            padding: 12px; 
            margin-top: 20px;
            background-color: #4CAF50; 
            color: #fff;
            font-size: 18px;
            font-weight: bold;
            border: none; 
            border-radius: 5px; 
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type=submit]:hover {
            background-color: #2196F3;
        }

        /* Home button */
        .home-button {
            margin-top: 15px;
            text-align: center;
        }

        .home-btn {
            display: inline-block;
            padding: 12px 25px;
            background-color: #FF4C4C; /* red */
            color: #fff;
            font-size: 16px;
            font-weight: bold;
            border-radius: 5px;
            text-decoration: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .home-btn:hover {
            background-color: #e60000;
        }

        /* Error message styling */
        .error { 
            color: red; 
            margin-top: 12px; 
            font-weight: bold;
            font-size: 16px;
            text-align: left; /* align with labels */
        }
    </style>
</head>
<body>
    <div class="login-container">
        <!-- Top logo image -->
        <img src="${pageContext.request.contextPath}/resources/images/admin login.png" class="top-logo" alt="Bank Logo">

        <h2>Admin Login</h2>

        <form action="${pageContext.request.contextPath}/admin/login" method="post">
            <label for="username">Username:</label>
            <input type="text" name="username" id="username" placeholder="Enter Username" required>

            <label for="password">Password:</label>
            <div class="password-container">
                <input type="password" name="password" id="password" placeholder="Enter Password" required>
                <span class="toggle-password" onclick="togglePassword()">👁️</span>
            </div>

            <input type="submit" value="Login">
        </form>

        <!-- Go to Home button -->
        <div class="home-button">
            <a href="${pageContext.request.contextPath}/index" class="home-btn">Go to Home</a>
        </div>

        <!-- Display error message if login fails -->
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
    </div>

    <script>
        function togglePassword() {
            var passwordInput = document.getElementById('password');
            var toggleIcon = document.querySelector('.toggle-password');
            if (passwordInput.type === "password") {
                passwordInput.type = "text";
                toggleIcon.textContent = "🙈";
            } else {
                passwordInput.type = "password";
                toggleIcon.textContent = "👁️";
            }
        }
    </script>
</body>
</html>
