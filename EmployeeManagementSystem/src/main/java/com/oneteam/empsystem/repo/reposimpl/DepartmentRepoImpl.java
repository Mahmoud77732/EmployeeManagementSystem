package com.oneteam.empsystem.repo.reposimpl;


import com.oneteam.empsystem.entity.Department;
import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.repo.repos.DepartmentRepo;
import org.hibernate.Session;

import java.util.List;

import static com.oneteam.empsystem.db.HibernateConnectionmanager.st_getSession;

public class DepartmentRepoImpl extends GenericRepoImpl<Department, Long> implements DepartmentRepo {

    public DepartmentRepoImpl() {
        super(Department.class);
    }

    /*
    public void remove(Long id) {
        try(Session session = st_getCurrentSession()){
            st_beginTransaction();
            Department department = session.get(Department.class, id);
            if (department != null) {
                session.delete(department);
            }
            st_commitTransaction();
        }catch (Exception e) {
            if (st_isActiveTransaction()) {
                st_rollbackTransaction();
            }
            System.err.println(e.getMessage());
        }
    }
    */

    @Override
    public List<Employee> getEmployees(Long departmentId) {
        return findById(departmentId).getEmployees();
    }

    @Override
    public Department findDepartmentByName(String departmentName) {
        String query = "select d from Department d where d.name = :name";
        try(Session session = st_getSession()){
            return session.createQuery(query, Department.class).setParameter("name", departmentName).uniqueResult();
        }
    }

}
