<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Manage Customers</title>
</head>
<body>
    <h2>Customer Accounts</h2>

    <table border="1" cellpadding="8">
        <tr>
            <th>ID</th>
            <th>Account No</th>
            <th>Email</th>
            <th>Registered</th>
            <th>Action</th>
        </tr>
        <!-- Example row, replace with JSTL loop when DB connected -->
        <tr>
            <td>1</td>
            <td>SB101</td>
            <td>customer1@gmail.com</td>
            <td>Yes</td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/viewCustomer?id=${customer.id}">View</a> |
                <a href="deleteCustomer?id=1">Delete</a>
            </td>
        </tr>
    </table>

    <br>
    <a href="${pageContext.request.contextPath}/admin/dashboard">← Back to Dashboard</a>
</body>
</html>
