<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, com.bank.model.Transactions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mini Statement</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: url("${pageContext.request.contextPath}/resources/images/bank.png") no-repeat center center fixed;
            background-size: cover;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        /* Top logo image */
        .top-image {
            margin-top: 20px;
            margin-bottom: 20px;
        }

        .top-image img {
            max-width: 150px;
            height: auto;
        }

        h2 {
            background-color: rgba(255,255,255,0.9);
            padding: 10px 20px;
            border-radius: 6px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.2);
            margin-bottom: 20px;
            text-align: center;
        }

        form {
            background: rgba(255,255,255,0.9);
            padding: 15px 20px;
            border-radius: 8px;
            border: 2px solid black;
            margin-bottom: 20px;
            text-align: center;
        }

        label {
            font-weight: bold;
            margin-right: 5px;
        }

        input[type="text"], button {
            padding: 8px;
            margin-top: 5px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 14px;
        }

        button {
            background-color: #007BFF;
            color: white;
            border: none;
            cursor: pointer;
            font-weight: bold;
            transition: 0.3s;
        }

        button:hover {
            background-color: #0056b3;
        }

        table {
            width: 90%;
            max-width: 800px;
            border-collapse: collapse;
            margin-bottom: 30px;
            background-color: rgba(255,255,255,0.95);
        }

        table, th, td {
            border: 2px solid black;
        }

        th, td {
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }

        /* Responsive for small screens */
        @media (max-width: 500px) {
            table, form {
                width: 95%;
            }
            .top-image img {
                max-width: 120px;
            }
        }
    </style>
</head>
<body>

    <!-- Top Image / Logo -->
    <div class="top-image">
        <img src="${pageContext.request.contextPath}/resources/images/mini statement.png" alt="Bank Logo">
    </div>

    <h2>Last 10 Transactions</h2>

    <form action="${pageContext.request.contextPath}/customer/transactions" method="get">
        <label>Account ID:</label>
        <input type="text" name="accountId" required>
        <button type="submit">Get Statement</button>
    </form>

    <table>
        <tr>
            <th>Txn ID</th>
            <th>From</th>
            <th>To</th>
            <th>Amount</th>
            <th>Date</th>
        </tr>
        <% 
            List<Transactions> txns = (List<Transactions>) request.getAttribute("txns");
            if (txns != null) {
                for (Transactions t : txns) {
        %>
        <tr>
            <td><%= t.getTxn_id() %></td>
            <td><%= t.getFrom_account_id() %></td>
            <td><%= t.getTo_account_id() %></td>
            <td><%= t.getAmount() %></td>
            <td><%= t.getTimestamp() %></td>
        </tr>
        <% }} %>
    </table>

</body>
</html>
