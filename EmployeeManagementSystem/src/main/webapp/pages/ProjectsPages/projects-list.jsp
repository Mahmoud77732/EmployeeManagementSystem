<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib uri="jakarta.tags.core" prefix="c" %>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Projects List</title>
</head>
<body>
    <h2>Projects List</h2>
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
        <c:forEach items="${projects}" var="project">
            <tr>
                <td>${project.projectId}</td>
                <td>${project.name}</td>
                <td>${project.description}</td>
                <td>
                    <ul>
                        <c:forEach items="${project.employees}" var="employee">
                            <li>${employee.name} - ${employee.email}</li>
                        </c:forEach>
                    </ul>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/updateProject?projectId=${project.projectId}">Edit</a>
                    <form action="${pageContext.request.contextPath}/deleteProject" method="get">
                        <input type="hidden" name="projectId" value="${project.projectId}" />
                        <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete this project?')" />
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <h2>Add new project</h2>
    <form action="${pageContext.request.contextPath}/addProject" method="post">
        Name: <input type="text" name="name" /><br>
        Description: <input type="text" name="description" /><br>
        <input type="submit" value="Add Proect" />
    </form>
    <h2>Assign employees to projects</h2>
    <a href="${pageContext.request.contextPath}/assignProjects">assign-projects</a>

    <br><br>
    <a href="${pageContext.request.contextPath}">start_page</a>
    <br><br>
    <a href="logout">logout</a>
</body>
</html>

