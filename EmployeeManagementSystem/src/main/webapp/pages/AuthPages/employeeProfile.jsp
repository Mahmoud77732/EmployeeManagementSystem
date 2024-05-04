
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Profile</title>
</head>
<body>
    <h2>Employee Profile</h2>
    <h3>Profile Details:</h3>
    <p><strong>Name:</strong> ${employee.name}</p>
    <p><strong>Email:</strong> ${employee.email}</p>
    <p><strong>Department:</strong> ${employee.department.name}</p>
    <p><strong>Salary:</strong> ${employee.salary}</p>

    <h3>Assigned Projects:</h3>
    <ul>
        <c:forEach var="project" items="${projects}">
            <li>${project.name}</li>
        </c:forEach>
    </ul>

    <p><strong>Password:</strong> ${user.password}</p>
    <p><strong>Role:</strong> ${user.role}</p>

    <a href="logout">logout</a>
</body>
</html>