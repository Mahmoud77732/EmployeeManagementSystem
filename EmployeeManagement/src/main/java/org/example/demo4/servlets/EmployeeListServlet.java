package org.example.demo4.servlets;

import org.example.demo4.entity.Employee;
import org.example.demo4.repo.EmployeeRepo;
import org.example.demo4.repo.EmployeeRepoImpl;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

//@WebServlet(name = "EmployeeListServlet", value = "/employees")
public class EmployeeListServlet extends HttpServlet {
    private final EmployeeRepo employeeRepo;

    public EmployeeListServlet() {
        this.employeeRepo = new EmployeeRepoImpl(); // Initialize your DAO here
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employees =  employeeRepo.findAll();
        request.setAttribute("employees", employees);
        System.out.println("==========================");
        System.out.println("======>" + employees.get(0).getName());
        request.getRequestDispatcher("/employee-list.jsp").forward(request, response);
    }

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            st_openSession();
//            st_beginTransaction();
//            List<Employee> employees =  employeeRepo.findAll();
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ListAllCitiesServlet</title>");
//            out.println("</head>");
//
//            out.println("<body>");
//            for (Employee employee : employees) {
//                out.println(employee.getId() + " | " + employee.getName());
//            }
//            out.println("</body>");
//
//            out.println("</html>");
//
//            st_commitTransaction();
//        }
//        catch(Exception ex){
//            System.err.println(ex.getMessage());
//            if(st_isActiveTransaction()){
//                st_rollbackTransaction();
//            }
//        }
//    }


}
