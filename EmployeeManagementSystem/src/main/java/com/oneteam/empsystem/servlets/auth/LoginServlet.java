package com.oneteam.empsystem.servlets.auth;

import com.oneteam.empsystem.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;

import static com.oneteam.empsystem.db.HibernateConnectionmanager.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isAuthenticated = authenticate(username, password);

        if (isAuthenticated) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            String userPageRole = getRedirectUrl(username);
            if(userPageRole.equals("hrDashboard.jsp")){
                session.setAttribute("role", "HR manager");
            }
            else{
                session.setAttribute("role", "Employee");
            }

            // HttpSession is used with sendRedirect()
            // response.sendRedirect(request.getContextPath() + "/pages/AuthPages/" + userPageRole);

            // u can use req.setAttribute instead of HttpSession
            request.getRequestDispatcher("pages/AuthPages/" + userPageRole).forward(request, response);
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("pages/AuthPages/login.jsp?error=true").forward(request, response);
            // response.sendRedirect("/login.jsp?error=true");
        }
    }

    private boolean authenticate(String username, String password) {
        try(Session session = st_getSession()) {
            st_beginTransaction();
            User user = getUserByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                return true;
            }
            st_commitTransaction();
        } catch (Exception ex) {
            if (st_getSession().getTransaction().isActive()) st_rollbackTransaction();
            System.err.println(ex.getMessage());
        }
        return false;
    }

    private String getRedirectUrl(String username) {
        User user = getUserByUsername(username);
        if (user != null && "HR manager".equals(user.getRole())) {
            return "hrDashboard.jsp";
        } else if (user != null && ("Employee".equals(user.getRole()))) {
            return "employeeDashboard.jsp";
        }
        else{
            return "pages/AuthPages/login.jsp";
        }
    }

    public User getUserByUsername(String username) {
        try(Session session = st_getSession()){
            String hql = "FROM User WHERE username = :username";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);
            return query.uniqueResult();
        }
    }
}
