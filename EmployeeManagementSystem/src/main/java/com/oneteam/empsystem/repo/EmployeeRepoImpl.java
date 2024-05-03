package com.oneteam.empsystem.repo;


import com.oneteam.empsystem.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static com.oneteam.empsystem.db.HibernateConnectionmanager.*;

public class EmployeeRepoImpl {

    public EmployeeRepoImpl() {
    }

    public void save(Employee employee) {
        try (Session session = st_openSession()) {
            st_beginTransaction();
            session.save(employee);
            st_commitTransaction();
        } catch (Exception e) {
            if (st_isActiveTransaction()) {
                st_rollbackTransaction();
            }
            System.err.println(e.getMessage());
        }
    }

    public Employee findById(Long id) {
        try (Session session = st_openSession()) {
            return session.get(Employee.class, id);
        }
    }

    public void update(Employee emp) {
        try(Session session = st_getCurrentSession()){
            st_beginTransaction();
            session.update(emp);
            st_commitTransaction();
        }catch (Exception e) {
            if (st_isActiveTransaction()) {
                st_rollbackTransaction();
            }
            System.err.println(e.getMessage());
        }
    }

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

    public List<Employee> findByDepartmentId(Long departmentId) {
        String jpqlQuery = "SELECT e FROM Employee e WHERE e.department.departmentId = :departmentId";
        try(Session session = st_openSession()){
            return session.createQuery(jpqlQuery, Employee.class)
                    .setParameter("departmentId", departmentId)
                    .getResultList();
        }
    }

    public List<Employee> findByProjectId(Long projectId) {
        String jpqlQuery = "SELECT e FROM Employee e JOIN e.projects p WHERE p.projectId = :projectId";
        try(Session session = st_openSession()){
            return session.createQuery(jpqlQuery, Employee.class)
                    .setParameter("projectId", projectId)
                    .getResultList();
        }
    }

}
