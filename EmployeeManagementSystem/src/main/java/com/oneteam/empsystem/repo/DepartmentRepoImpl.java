package com.oneteam.empsystem.repo;

import com.oneteam.empsystem.entity.Department;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static com.oneteam.empsystem.db.HibernateConnectionmanager.*;

public class DepartmentRepoImpl {

    public DepartmentRepoImpl() {
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

    public Department findById(Long id) {
        try (Session session = st_openSession()) {
            return session.get(Department.class, id);
        }
    }

    public void update(Department department) {
        try(Session session = st_getCurrentSession()){
            st_beginTransaction();
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

}
