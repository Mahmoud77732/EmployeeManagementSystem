
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>HR - Employee Management System</title>
</head>
<body>
    <%--
    <%
        if(session.getAttribute("username") == null || !session.getAttribute("role").equals("HR manager")){
            response.sendRedirect("/login");
        }
    %>
    --%>

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