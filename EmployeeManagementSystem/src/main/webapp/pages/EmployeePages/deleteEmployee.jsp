<%--
  Created by IntelliJ IDEA.
  User: mahmoud
  Date: 1‏/5‏/2024
  Time: 12:32 ص
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Employee</title>
</head>
<body>
    <h1>Delete Employee</h1>
    <p>Are you sure you want to remove this employee?</p>
    <form action="deleteEmployee" method="get">
        <input type="hidden" name="id" value="${employee.id}" />
        <input type="submit" value="Delete" />
    </form>
    <a href="${pageContext.request.contextPath}/employees">Cancel</a>
</body>
</html>
