<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Balance</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background-color: #f9f9f9;
        }
        h1 { color: #003366; }
        .error { color: red; font-weight: bold; }
        .success { color: green; font-weight: bold; }
        .warning { color: #cc6600; font-weight: bold; }
        a { color: #0066cc; text-decoration: none; }
        a:hover { text-decoration: underline; }
        .box {
            background: #fff;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            max-width: 400px;
        }
    </style>
</head>
<body>

<h1>Account Balance</h1>

<%
    String error = (String) request.getAttribute("error");
    Object accountId = request.getAttribute("accountId");
    Object balance = request.getAttribute("balance");
    Object minBalance = request.getAttribute("minBalance");
%>

<div class="box">
    <% if (error != null) { %>
        <p class="error"><%= error %></p>
    <% } else if (balance != null && minBalance != null) { %>

        <p><strong>Account ID:</strong> <%= accountId %></p>
        <p><strong>Available Balance:</strong> 
            <span class="<%= ((java.math.BigDecimal) balance).compareTo((java.math.BigDecimal) minBalance) >= 0 ? "success" : "warning" %>">
                <%= balance %>
            </span>
        </p>

        <p><strong>Minimum Required Balance:</strong> <%= minBalance %></p>

        <% if (((java.math.BigDecimal) balance).compareTo((java.math.BigDecimal) minBalance) < 0) { %>
            <p class="warning">⚠ Your balance is below the minimum required.</p>
        <% } %>

    <% } else { %>
        <p class="error">Balance information is unavailable.</p>
    <% } %>
</div>

<br>
<a href="<%= request.getContextPath() %>/customer/dashboard">⬅ Back to Dashboard</a>

</body>
</html>
