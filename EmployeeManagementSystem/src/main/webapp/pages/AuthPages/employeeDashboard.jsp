
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Management System</title>
</head>
<body>

    <%--
    <%
        if(session.getAttribute("username") == null || !session.getAttribute("role").equals("Employee")){
            response.sendRedirect("/login");
        }
    %>
    --%>

    <h1><%= "Employee Management System!" %>
    </h1>
    <br/>
    <a href="employees">Employee_List</a>
    <br>
    <a href="empProfile">My Profile</a>
    <br>
    <p>Role: ${role}</p>
</body>
</html>