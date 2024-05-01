
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Edit Department</title>
</head>
<body>
<h1>Edit Department</h1>
<form action="updateDepartment" method="post">
    <input type="hidden" name="departmentId" value="${department.departmentId}" />
    Name: <input type="text" name="name" value="${department.name}" /><br>
    Description: <input type="text" name="description" value="${employee.description}" /><br>
    <input type="submit" value="Update" />
</form>
</body>
</html>
