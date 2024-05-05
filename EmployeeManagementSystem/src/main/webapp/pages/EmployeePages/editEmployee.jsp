
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Employee</title>
</head>
<body>
<h1>Edit Employee</h1>
    <form action="updateEmployee" method="post">
        <input type="hidden" name="id" value="${employee.id}" />
        Name: <input type="text" name="name" value="${employee.name}" /><br>
        Email: <input type="text" name="email" value="${employee.email}" /><br>
        Department Name: <input type="text" name="departmentName" value="${employee.department.name}" /><br>
        Salary: <input type="text" name="salary" value="${employee.salary}" /><br>
        <input type="submit" value="Update" />
    </form>

    <c:if test="${departmentNameNotFound eq true}">
        <p style="color: tomato">you entered wrong department name</p>
    </c:if>

</body>
</html>

