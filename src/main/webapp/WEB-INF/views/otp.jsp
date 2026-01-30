<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>OTP Verification</title>
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

        /* OTP container */
        .otp-container {
            background: linear-gradient(to bottom, #FFFFFF, #E0F2FF); /* light gradient inside */
            border: 3px solid black;  /* black border */
            border-radius: 15px;
            padding: 30px 50px;
            box-shadow: 0 5px 20px rgba(0,0,0,0.3);
            text-align: center;
            max-width: 400px;
            width: 100%;
        }

        /* Top banner image */
        .otp-container img {
            width: 100px;
            margin-bottom: 20px;
        }

        h2 {
            color: #1E3A8A;
            font-weight: 900;
            margin-bottom: 25px;
        }

        /* Form inputs */
        input[type="text"], input[type="hidden"] {
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

        /* Error message */
        .error {
            color: red;
            margin-bottom: 10px;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="otp-container">
        <!-- Top banner image -->
        <img src="${pageContext.request.contextPath}/resources/images/otp.png" alt="Bank Logo">

        <h2>OTP Verification</h2>

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/customer/validateOtp" method="post">
            <!-- Pass customerId -->
            <input type="hidden" name="customerId" value="${customerId}" />

            <input type="text" name="otp" placeholder="Enter OTP" required /><br><br>

            <button type="submit">Verify</button>
        </form>
    </div>
</body>
</html>
