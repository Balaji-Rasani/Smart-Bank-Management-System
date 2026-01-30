<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Fund Transfer</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            color: #333;
            max-width: 600px;
            margin: 40px auto;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            color: #0056b3;
        }
        label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
        }
        input {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }
        button {
            margin-top: 20px;
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 15px;
        }
        button:hover {
            background-color: #0056b3;
        }
        .message {
            color: green;
            font-weight: bold;
        }
        .error {
            color: red;
            font-weight: bold;
        }
        a {
            display: inline-block;
            margin-top: 15px;
            text-decoration: none;
            color: #007bff;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h2>Fund Transfer</h2>

    <c:if test="${not empty message}">
        <p class="message">${message}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/customer/fundTransfer" method="post">

        <label for="fromAccountNo">From Account Number:</label>
        <input type="text" id="fromAccountNo" name="fromAccountNo" required
               placeholder="Enter your account number"
               pattern="^[a-zA-Z0-9]+$"
               title="Only letters and numbers allowed"
               oninput="this.value = this.value.replace(/[^a-zA-Z0-9]/g, '');">

        <label for="toAccountNo">To Account Number:</label>
        <input type="text" id="toAccountNo" name="toAccountNo" required
               placeholder="Enter recipient account number"
               pattern="^[a-zA-Z0-9]+$"
               title="Only letters and numbers allowed"
               oninput="this.value = this.value.replace(/[^a-zA-Z0-9]/g, '');">

        <label for="amount">Amount:</label>
        <input type="text" id="amount" name="amount" required
               pattern="^[0-9]+(\\.[0-9]{1,2})?$"
               title="Enter a valid amount (e.g., 100 or 100.50)"
               placeholder="Enter amount"
               oninput="this.value = this.value.replace(/[^0-9.]/g, '');">

        <button type="submit">Transfer</button>
    </form>

    <a href="${pageContext.request.contextPath}/customer/dashboard">⬅ Back to Dashboard</a>
</body>
</html>
