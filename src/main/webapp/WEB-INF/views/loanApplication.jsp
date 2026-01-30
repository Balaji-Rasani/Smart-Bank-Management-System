<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Loan Application</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: url("${pageContext.request.contextPath}/resources/images/bank.png") no-repeat center center fixed;
            background-size: cover;
            min-height: 100vh;
            display: flex;
            justify-content: center; 
            align-items: center;
        }

        .loan-container {
            background: rgba(255, 255, 255, 0.95);
            padding: 30px 40px;
            border-radius: 12px;
            box-shadow: 0 6px 15px rgba(0,0,0,0.3);
            max-width: 400px;
            width: 100%;
            border: 2px solid black;
            text-align: center;
        }

        .form-image {
            max-width: 120px;
            margin-bottom: 20px;
        }

        h2 {
            color: #333;
            margin-bottom: 25px;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
            text-align: left;
        }

        input[type="text"], 
        input[type="number"], 
        input[type="submit"], 
        .back-button {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            box-sizing: border-box;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 14px;
        }

        input[type="submit"] {
            background-color: #007BFF;
            color: white;
            border: none;
            margin-top: 25px;
            cursor: pointer;
            font-weight: bold;
            transition: 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .back-button {
            background-color: #6c757d;
            color: white;
            border: none;
            margin-top: 15px;
            cursor: pointer;
            font-weight: bold;
            text-decoration: none;
            display: inline-block;
            text-align: center;
        }

        .back-button:hover {
            background-color: #5a6268;
        }

        .message {
            margin-top: 20px;
            color: green;
            font-weight: bold;
        }

        .error {
            margin-top: 20px;
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div class="loan-container">

    <img src="${pageContext.request.contextPath}/resources/images/loan application.png" 
         alt="Loan Image" class="form-image"/>

    <h2>Apply for a Loan</h2>

    <form action="${pageContext.request.contextPath}/customer/loan/apply" method="post">

        <label for="accountNo">Account Number:</label>
        <input type="text" name="accountNo" required />

        <label for="amount">Loan Amount:</label>
        <input type="number" name="amount" step="0.01" required />

        <label for="duration">Duration (in months):</label>
        <input type="number" name="duration" required />

        <input type="submit" value="Submit Loan Application" />
    </form>

    <!-- ⭐ Back to Dashboard Button -->
    <a href="${pageContext.request.contextPath}/customer/dashboard" class="back-button">Back to Dashboard</a>

    <div class="message">${message}</div>
    <div class="error">${error}</div>

</div>

</body>	
</html>
