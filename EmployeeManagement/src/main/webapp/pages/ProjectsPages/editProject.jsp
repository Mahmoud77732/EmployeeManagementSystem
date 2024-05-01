
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Edit Project</title>
</head>
<body>
    <h1>Edit Project</h1>
    <form action="updateProject" method="post">
        <input type="hidden" name="projectId" value="${project.projectId}" />
        Name: <input type="text" name="name" value="${project.name}" /><br>
        Description: <input type="text" name="description" value="${project.description}" /><br>
        <h2>Employees</h2>
        <select name="employeeIds" multiple>
            <c:forEach var="employee" items="${allEmployees}">
                <option value="${employee.id}" <c:if test="${department.employees.contains(employee)}">selected</c:if>>
                        ${employee.name}
                </option>
            </c:forEach>
        </select><br/>
        <input type="submit" value="Update" />
    </form>
</body>
</html>
