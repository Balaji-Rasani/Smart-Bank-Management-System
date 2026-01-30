<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mini Statement</title>
</head>
<body>
    <h1>Recent Transactions</h1>

    <!-- Error -->
    <c:if test="${not empty error}">
        <p style="color:red; font-weight:bold;">${error}</p>
    </c:if>

    <!-- Transactions Table -->
    <c:if test="${not empty transactions}">
        <table border="1" cellpadding="8" cellspacing="0">
            <tr>
                <th>Date</th>
                <th>Description</th>
                <th>Amount</th>
                <th>Balance</th>
            </tr>
            <c:forEach var="txn" items="${transactions}">
                <tr>
                    <td><c:out value="${txn.date}"/></td>
                    <td><c:out value="${txn.description}"/></td>
                    <td><c:out value="${txn.amount}"/></td>
                    <td><c:out value="${txn.balance}"/></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <br/>
    <a href="${pageContext.request.contextPath}/customer/dashboard">⬅ Back to Dashboard</a>
</body>
</html>
