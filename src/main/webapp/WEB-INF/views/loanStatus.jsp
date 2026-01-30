<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.bank.model.Loan_Applications" %>
<html>
<head>
    <title>Loan Status</title>
</head>
<body>
    <h2>Check Loan Status</h2>

    <!-- ✅ Corrected form action -->
    <form action="${pageContext.request.contextPath}/customer/loan/status" method="get">
        <label>Loan ID:</label>
        <input type="text" name="loanId" required><br><br>
        <button type="submit">Check Status</button>
    </form>

    <hr>

    <!-- ✅ Display multiple loans (if the controller returns a list) -->
    <c:if test="${not empty loans}">
        <h3>Your Loan Details:</h3>
        <table border="1" cellpadding="5">
            <tr>
                <th>Loan ID</th>
                <th>Status</th>
                <th>Amount</th>
                <th>Duration</th>
                <th>Applied On</th>
            </tr>
            <c:forEach var="loan" items="${loans}">
                <tr>
                    <td>${loan.loan_id}</td>
                    <td>${loan.status}</td>
                    <td>${loan.amount}</td>
                    <td>${loan.duration}</td>
                    <td>${loan.applied_on}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty loans}">
        <p>No loan records found.</p>
    </c:if>

    <hr>
     <!-- Back to Dashboard link -->
    <a href="<%= request.getContextPath() %>/customer/dashboard">Back to Dashboard</a>
</body>
</html>
