package com.oneteam.empsystem.servlets.auth;

import com.oneteam.empsystem.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;

import static com.oneteam.empsystem.db.HibernateConnectionmanager.*;

public class AuthorizationFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);
        boolean isLoggedIn = (session != null && session.getAttribute("username") != null);

        if (isLoggedIn) {
            String username = (String) session.getAttribute("username");
            User user = getUserByUsername(username);

            if (user != null && ("HR manager".equals(user.getRole()) && httpRequest.getRequestURI().startsWith("/hr/")) ||
                    ("Employee".equals(user.getRole()) && httpRequest.getRequestURI().startsWith("/employee/"))) {
                chain.doFilter(request, response);
            } else {
                httpResponse.sendRedirect("pages/AuthPages/login.jsp");
            }
        } else {
            httpResponse.sendRedirect("pages/AuthPages/login.jsp");
        }
    }

    // Helper method to get user by username
    public User getUserByUsername(String username) {
        try(Session session = st_openSession()){
            String hql = "FROM User WHERE username = :username";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);
            return query.uniqueResult();
        }
    }
}
