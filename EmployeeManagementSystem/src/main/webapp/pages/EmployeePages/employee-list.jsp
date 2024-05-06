
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
            <c:if test="${userPageRole eq 'HR manager'}">
                <th>Salary</th>
                <th>Action</th>
            </c:if>
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
                <c:if test="${userPageRole eq 'HR manager'}">
                    <td>${employee.salary}</td>
                    <td>
                        <%--
                            when you use '/' before the _path_ then you want to go to another context
                            if you didn't use it then you tell it that there is an endpoint on this context
                        --%>
                        <a href="updateEmployee?id=${employee.id}">Edit</a>
                        <a href="deleteEmployee?id=${employee.id}">Delete</a>

                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <c:if test="${userPageRole eq 'HR manager'}">
        <a href="pages/EmployeePages/addEmployee.jsp">Add New Employee</a>
        <br><br>
        <%-- <a href="login">start_page</a>--%>
    </c:if>
    <%-- <a href="${pageContext.request.contextPath}/pages/AuthPages/employeeDashboard.jsp">start_page</a>--%>
</body>
</html>
