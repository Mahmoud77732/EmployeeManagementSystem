package com.oneteam.empsystem.repo.reposimpl;


import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.repo.repos.EmployeeRepo;
import org.hibernate.Session;

import java.util.List;

import static com.oneteam.empsystem.db.HibernateConnectionmanager.*;

public class EmployeeRepoImpl extends GenericRepoImpl<Employee, Long> implements EmployeeRepo {

    public EmployeeRepoImpl() {
        super(Employee.class);
    }


    /*
    public void remove(Long id) {
        try(Session session = st_getCurrentSession()){
            st_beginTransaction();
            Employee employee = session.get(Employee.class, id);
            if (employee != null) {
                session.delete(employee);
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
    public List<Employee> findByDepartmentId(Long departmentId) {
        String jpqlQuery = "SELECT e FROM Employee e WHERE e.department.departmentId = :departmentId";
        try(Session session = st_getSession()){
            return session.createQuery(jpqlQuery, Employee.class)
                    .setParameter("departmentId", departmentId)
                    .getResultList();
        }
    }

    @Override
    public List<Employee> findByProjectId(Long projectId) {
        String jpqlQuery = "SELECT e FROM Employee e JOIN e.projects p WHERE p.projectId = :projectId";
        try(Session session = st_getSession()){
            return session.createQuery(jpqlQuery, Employee.class)
                    .setParameter("projectId", projectId)
                    .getResultList();
        }
    }

    /*
    public List<Employee> findAll(){
        try(Session session = st_openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
            Root<Employee> root = criteria.from(Employee.class);
            criteria.select(root);
            Query<Employee> query = session.createQuery(criteria);
            return query.getResultList();
        }
    }

    public List<Employee> findAll2() {
        try (Session session = st_getCurrentSession()) {
            return session.createQuery("FROM Employee", Employee.class).getResultList();
        }

    }
    */

}
