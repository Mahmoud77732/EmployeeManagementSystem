
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>HR - Employee Management System</title>
</head>
<body>
    <%
        <%-- prevent back to this page after logout --%>
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
    %>

    <h1><%= "HR - Employee Management System!" %>
    </h1>
    <br/>
    <a href="employees">Employee_List</a>
    <br><br>
    <a href="departments">Department_List</a>
    <br><br>
    <a href="projects">Project_List</a>
    <br><br>
     <p>Role: ${role}</p>
    <br><br>
    <a href="logout">logout</a>
</body>
</html>