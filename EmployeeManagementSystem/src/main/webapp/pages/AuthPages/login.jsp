

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%--    <%--%>
<%--        if(session.getAttribute("role") != null){--%>
<%--            String role = session.getAttribute("role").toString();--%>
<%--            System.out.println("=====> " + role);--%>
<%--            if(role.equals("HR manager")){--%>
<%--                response.sendRedirect("login");--%>
<%--            } else if (role.equals("Employee")) {--%>
<%--                response.sendRedirect("login");--%>
<%--            }--%>
<%--        }--%>
<%--    %>--%>
    <h2>Login</h2>
    <form action="login" method="get">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required placeholder="enter your username"><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        <input type="submit" value="Login">
        <% if (request.getAttribute("error") != null) { %>
        <p style="color: red;"><%= request.getAttribute("error") %></p>
        <% } %>
    </form>
    <br>
    <a href="register">register</a>
</body>
</html>