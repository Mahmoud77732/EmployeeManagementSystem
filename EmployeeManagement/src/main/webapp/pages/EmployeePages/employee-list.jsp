<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib uri="jakarta.tags.core" prefix="c" %>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employees List</title>
    <style>
        table{
            text-align: center;
        }
        table ul{
            text-align: center;
            list-style: none;
            text-decoration: none;
        }
    </style>
</head>
<body>
<h2>Employees List</h2>
<table border="2" cellpadding="10">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Department Name</th>
        <th>Projects</th>
        <th>Salary</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${employees}" var="employee">
        <tr>
            <td>${employee.id}</td>
            <td>${employee.name}</td>
            <td>${employee.email}</td>
            <td>${employee.department.name}</td>
            <td>
                <ul>
                    <c:forEach items="${employee.projects}" var="project">
                        <li>_ ${project.name} _</li>
                    </c:forEach>
                </ul>
            </td>
            <td>${employee.salary}</td>
            <td>
                <a href="updateEmployee?id=${employee.id}">Edit</a>
                <a href="deleteEmployee?id=${employee.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<a href="pages/EmployeePages/addEmployee.jsp">Add New Employee</a>
</body>
</html>
