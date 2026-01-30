<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Loan Status</title>
</head>
<body>
    <h1>Loan Status</h1>

    <!-- Check Loan by ID Form -->
    <form action="${pageContext.request.contextPath}status" method="get">
        Loan ID: <input type="text" name="loanId" />
        <button type="submit">Check Status</button>
    </form>
    <br/>

    <!-- Error Message -->
    <c:if test="${not empty error}">
        <p style="color:red;"><b>${error}</b></p>
    </c:if>

    <!-- Loan Table -->
    <c:if test="${not empty loans}">
        <table border="1" cellpadding="8" cellspacing="0">
            <tr>
                <th>Loan ID</th>
                <th>Amount</th>
                <th>Duration (Months)</th>
                <th>Status</th>
                <th>Applied On</th>
            </tr>
            <c:forEach var="loan" items="${loans}">
                <tr>
                    <td>${loan.loan_id}</td>
                    <td>$${loan.amount}</td>
                    <td>${loan.duration}</td>
                    <td>${loan.status}</td>
                    <td>${loan.applied_on}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <!-- No Loans Case -->
    <c:if test="${empty loans and empty error}">
        <p>No loan applications found.</p>
    </c:if>

    <br/>
    <a href="${pageContext.request.contextPath}/customer/dashboard">Back to Dashboard</a>
</body>
</html>
