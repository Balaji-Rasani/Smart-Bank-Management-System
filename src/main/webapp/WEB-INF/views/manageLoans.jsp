<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Manage Loans</title>
</head>
<body>
    <h2>Loan Applications</h2>

    <c:if test="${empty loans}">
        <p>No loans available.</p>
    </c:if>

    <c:if test="${not empty loans}">
        <table border="1" cellpadding="8">
            <tr>
                <th>Loan ID</th>
                <th>Customer ID</th>
                <th>Amount</th>
                <th>Status</th>
                <th>Action</th>
            </tr>

            <c:forEach var="loan" items="${loans}">
                <tr>
                    <td>${loan.loanId}</td>
                    <td>${loan.customerId}</td>
                    <td>${loan.amount}</td>
                    <td>${loan.status}</td>
                    <td>
                        <!-- Approve -->
                        <form action="${pageContext.request.contextPath}/admin/approveLoan" method="post" style="display:inline;">
                            <input type="hidden" name="loanId" value="${loan.loanId}">
                            <input type="hidden" name="status" value="Approved">
                            <button type="submit">Approve</button>
                        </form>

                        <!-- Reject -->
                        <form action="${pageContext.request.contextPath}/admin/approveLoan" method="post" style="display:inline;">
                            <input type="hidden" name="loanId" value="${loan.loanId}">
                            <input type="hidden" name="status" value="Rejected">
                            <button type="submit">Reject</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <br>
    <a href="${pageContext.request.contextPath}/admin/dashboard">← Back to Dashboard</a>
</body>
</html>
