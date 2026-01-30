<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Reports</title>
</head>
<body>
    <h2>Generate Reports</h2>
    <form action="generateReport" method="post">
        <label>Select Report Type:</label>
        <select name="reportType">
            <option value="transactions">Transactions Report</option>
            <option value="loans">Loans Report</option>
            <option value="customers">Customers Report</option>
        </select>
        <br><br>
        <button type="submit">Generate</button>
    </form>

    <br>
    <a href="adminDashboard">← Back to Dashboard</a>
</body>
</html>
