
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Add New Employee</title>
</head>
<body>
    <h1>Add New Employee</h1>
    <form action="${pageContext.request.contextPath}/addEmployee" method="post">
        Name: <input type="text" name="name" /><br>
        mail: <input type="text" name="email" /><br>
        Department ID: <input type="text" name="departmentId" /><br>
        Salary: <input type="text" name="salary" /><br>
        <input type="submit" value="Add" />
    </form>
</body>
</html>
