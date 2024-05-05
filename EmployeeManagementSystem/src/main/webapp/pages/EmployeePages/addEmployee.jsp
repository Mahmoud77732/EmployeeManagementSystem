
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Add New Employee</title>
</head>
<body>
    <h1>Add New Employee</h1>
    <form action="${pageContext.request.contextPath}/addEmployee" method="post">
        Name: <input type="text" name="name" /><br>
        mail: <input type="text" name="email" /><br>
        <%-- Department ID: <input type="text" name="departmentId" /><br>--%>
        Department Name: <input type="text" name="departmentName" /><br>
        Salary: <input type="text" name="salary" /><br>
        Default Password: <input type="password" name="password"><br>
        Default Role: <input type="text" name="role"><br>
        <input type="submit" value="Add" />
    </form>

    <c:if test="${not empty error}">
        <p style="color: tomato">${error}</p>
    </c:if>

</body>
</html>
