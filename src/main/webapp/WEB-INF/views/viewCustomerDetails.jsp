<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Customer Details</title>
</head>
<body>
    <h2>Customer Details</h2>

    <!-- Check if customer exists -->
    <c:if test="${not empty customer}">
        <table border="1" cellpadding="8">
            <tr>
                <th>ID</th>
                <td>${customer.id}</td>
            </tr>
            <tr>
                <th>Account No</th>
                <td>${customer.account_no}</td>
            </tr>
            <tr>
                <th>Email</th>
                <td>${customer.email}</td>
            </tr>
            <tr>
                <th>Registered</th>
                <td>
                    <c:choose>
                        <c:when test="${customer.registered}">Yes</c:when>
                        <c:otherwise>No</c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>
    </c:if>

    <!-- Message if customer not found -->
    <c:if test="${empty customer}">
        <p>Customer not found.</p>
    </c:if>

    <br>
    <a href="${pageContext.request.contextPath}/admin/manageCustomers">← Back to Customer List</a>
</body>
</html>
