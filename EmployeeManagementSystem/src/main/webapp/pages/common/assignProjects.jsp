
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Assign Projects</title>
</head>
<body>
    <h1>Assign Projects to Employees</h1>
    <form action="assignProjects" method="post">
        <label>Select Employee:</label>
        <select name="id">
            <c:forEach var="employee" items="${employees}">
                <option value="${employee.id}">${employee.name}</option>
            </c:forEach>
        </select>
        <br>
        <label>Select Project:</label>
        <select name="projectId">
            <c:forEach var="project" items="${projects}">
                <option value="${project.projectId}">${project.name}</option>
            </c:forEach>
        </select>
        <br>
        <input type="submit" value="Assign Project">
    </form>
    <br><br>
    <a href="${pageContext.request.contextPath}">start_page</a>
</body>
</html>