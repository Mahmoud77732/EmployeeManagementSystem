package com.oneteam.empsystem.servlets.auth;

import com.oneteam.empsystem.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;

import static com.oneteam.empsystem.db.HibernateConnectionmanager.*;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isAuthenticated = authenticate(username, password);

        if (isAuthenticated) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            String userPageRole = getRedirectUrl(username);
            if(userPageRole.equals("hr-dashboard.jsp")){
                request.setAttribute("userPageRole", "hr-dashboard.jsp");
            }
            else if(userPageRole.equals("employee-dashboard.jsp")){
                request.setAttribute("userPageRole", "employee-dashboard.jsp");
            }
            response.sendRedirect(userPageRole);
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("pages/AuthPages/login.jsp").forward(request, response);
        }
    }

    private boolean authenticate(String username, String password) {
        try(Session session = st_openSession()) {
            st_beginTransaction();
            User user = getUserByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                return true;
            }
            st_commitTransaction();
        } catch (Exception ex) {
            if (st_isActiveTransaction()) st_rollbackTransaction();
            System.err.println(ex.getMessage());
        }
        return false;
    }

    private String getRedirectUrl(String username) {
        User user = getUserByUsername(username);
        if (user != null && "HR manager".equals(user.getRole())) {
            return "hr-dashboard.jsp";
        } else if (user != null && "Employee".equals(user.getRole())) {
            return "employee-dashboard.jsp";
        }
        return "pages/AuthPages/login.jsp";
    }

    public User getUserByUsername(String username) {
        try(Session session = st_openSession()){
            String hql = "FROM User WHERE username = :username";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);
            return query.uniqueResult();
        }
    }
}
