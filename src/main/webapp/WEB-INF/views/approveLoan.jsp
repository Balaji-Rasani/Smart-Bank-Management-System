<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Loan Action Status</title>
</head>
<body>
    <h2>Loan Status Update</h2>

    <c:if test="${param.success == 'true'}">
        <p style="color:green;">Loan status updated successfully!</p>
    </c:if>

    <c:if test="${param.error == 'true'}">
        <p style="color:red;">Failed to update loan status.</p>
    </c:if>

    <c:if test="${param.error == 'missingParams'}">
        <p style="color:red;">Loan ID or status missing!</p>
    </c:if>

    <br>
    <a href="${pageContext.request.contextPath}/admin/manageLoans">← Back to Manage Loans</a>
</body>
</html>
