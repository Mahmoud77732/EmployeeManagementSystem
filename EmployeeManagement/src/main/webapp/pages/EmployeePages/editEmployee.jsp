
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        Department ID: <input type="text" name="departmentId" value="${employee.department.departmentId}" /><br>
        Salary: <input type="text" name="salary" value="${employee.salary}" /><br>
        <input type="submit" value="Update" />
    </form>
</body>
</html>

