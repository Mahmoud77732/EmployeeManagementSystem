<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib uri="jakarta.tags.core" prefix="c" %>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Departments List</title>
</head>
<body>
    <h2>Departments List</h2>
    <table border="2" cellpadding="10">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Employees</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${departments}" var="department">
            <tr>
                <td>${department.departmentId}</td>
                <td>${department.name}</td>
                <td>${department.description}</td>
                <td>
                    <ul>
                        <c:forEach items="${department.employees}" var="employee">
                            <li>${employee.name} - ${employee.email}</li>
                        </c:forEach>
                    </ul>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/updateDepartment?departmentId=${department.departmentId}">Edit</a>
                    <form action="${pageContext.request.contextPath}/deleteDepartment" method="get">
                        <input type="hidden" name="departmentId" value="${department.departmentId}" />
                        <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete this department?')" />
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <h2>Add new department</h2>
    <form action="${pageContext.request.contextPath}/addDepartment" method="post">
        Name: <input type="text" name="name" /><br>
        Description: <input type="text" name="description" /><br>
        <input type="submit" value="Add Department" />
    </form>
</body>
</html>

