package com.oneteam.empsystem.servlets.auth;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("username");
            session.removeAttribute("role");
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath());
        // request.getRequestDispatcher("pages/AuthPages/login.jsp").forward(request, response);
    }
}

