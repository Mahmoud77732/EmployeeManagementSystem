package org.example.demo4.depservlets;

import org.example.demo4.entity.Department;
import org.example.demo4.entity.Employee;
import org.example.demo4.repo.DepartmentRepoImpl;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.example.demo4.repo.EmployeeRepo;
import org.example.demo4.repo.EmployeeRepoImpl;

import java.io.IOException;
import java.math.BigDecimal;

public class EditDepartmentServlet extends HttpServlet {

    private final DepartmentRepoImpl departmentRepo;

    public EditDepartmentServlet() {
        this.departmentRepo = new DepartmentRepoImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long departmentId = Long.parseLong(request.getParameter("departmentId"));
        Department department = departmentRepo.findById(departmentId);
        request.setAttribute("department", department);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/DepartmentsPages/editDepartment.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long departmentId = Long.parseLong(request.getParameter("departmentId"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Department department = departmentRepo.findById(departmentId);
        if (department != null) {
            System.out.println("=1===> " + department.getDepartmentId());
            department.setDepartmentId(departmentId);
            department.setName(name);
            department.setDescription(description);

            departmentRepo.update(department);
        }

        System.out.println("=2===> " + department.getDepartmentId());

        response.sendRedirect(request.getContextPath() + "/departments"); // Redirect to employee list page
    }

}
