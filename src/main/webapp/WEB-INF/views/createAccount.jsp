<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Create Customer Account</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }
        .container {
            width: 500px;
            margin: 50px auto;
            padding: 25px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        input[type=text], input[type=password], input[type=email], input[type=number], input[type=datetime-local] {
            width: 100%;
            padding: 10px;
            margin: 8px 0 15px 0;
            box-sizing: border-box;
        }
        button {
            width: 100%;
            padding: 12px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #45a049;
        }
        .message {
            margin-bottom: 15px;
            padding: 10px;
            border-radius: 4px;
        }
        .success {
            background-color: #d4edda;
            color: #155724;
        }
        .error {
            background-color: #f8d7da;
            color: #721c24;
        }
        a {
            display: block;
            margin-top: 20px;
            text-decoration: none;
            color: #333;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Create New Customer Account</h2>

        <!-- Success/Error messages -->
        <c:if test="${not empty success}">
            <div class="message success">${success}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="message error">${error}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/admin/createCustomer" method="post">
            <label>Customer ID (optional):</label>
            <input type="text" name="customerId">

            <label>Account Number:</label>
            <input type="text" name="accountNo" required>

            <label>Email:</label>
            <input type="email" name="email" required>

            <label>Password:</label>
            <input type="password" name="password" required>

            <label>Balance:</label>
            <input type="number" step="0.01" name="balance" required>

            <label>Created At (optional):</label>
            <input type="datetime-local" name="createdAt">

            <label>Updated At (optional):</label>
            <input type="datetime-local" name="updatedAt">

            <button type="submit">Create Account</button>
        </form>

        <a href="${pageContext.request.contextPath}/admin/dashboard">← Back to Dashboard</a>
    </div>
</body>
</html>
