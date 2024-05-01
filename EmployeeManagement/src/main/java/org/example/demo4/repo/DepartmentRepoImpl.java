package org.example.demo4.repo;

import org.example.demo4.entity.Department;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.example.demo4.db.HibernateConnectionmanager.*;

public class DepartmentRepoImpl {

    public DepartmentRepoImpl() {
    }

    public List<Department> findAll() {
        try(Session session = st_openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Department> criteria = builder.createQuery(Department.class);
            Root<Department> root = criteria.from(Department.class);
            criteria.select(root);
            Query<Department> query = session.createQuery(criteria);
            return query.getResultList();
            // return session.createQuery("FROM Department", Department.class).getResultList();
        }
    }

    public Department findById(Long id) {
        try (Session session = st_openSession()) {
            return session.get(Department.class, id);
        }
    }

    public void save(Department department) {
        try (Session session = st_openSession()) {
            st_beginTransaction();
            session.save(department);
            st_commitTransaction();
        } catch (Exception e) {
            if (st_isActiveTransaction()) {
                st_rollbackTransaction();
            }
            System.err.println(e.getMessage());
        }
    }

    public void update(Department department) {
        try(Session session = st_getCurrentSession()){
            st_beginTransaction();
            System.out.println("from update(): " + department.getEmployees().get(0).getId() + " " + department.getEmployees().get(0).getName());
            session.update(department);
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
            Department department = session.get(Department.class, id);
            if (department != null) {
                System.out.println("===> from remove(): " + department.getDepartmentId() + " " + department.getName());
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

}
