package org.example.demo4.repo;

import org.example.demo4.entity.Project;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.example.demo4.db.HibernateConnectionmanager.*;

public class ProjectRepoImpl {

    public ProjectRepoImpl() {
    }

    public List<Project> findAll() {
        try(Session session = st_openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Project> criteria = builder.createQuery(Project.class);
            Root<Project> root = criteria.from(Project.class);
            criteria.select(root);
            Query<Project> query = session.createQuery(criteria);
            return query.getResultList();
            // return session.createQuery("FROM Department", Department.class).getResultList();
        }
    }

    public Project findById(Long id) {
        try (Session session = st_openSession()) {
            return session.get(Project.class, id);
        }
    }

    public void save(Project project) {
        try (Session session = st_openSession()) {
            st_beginTransaction();
            session.save(project);
            st_commitTransaction();
        } catch (Exception e) {
            if (st_isActiveTransaction()) {
                st_rollbackTransaction();
            }
            System.err.println(e.getMessage());
        }
    }

    public void update(Project project) {
        try(Session session = st_getCurrentSession()){
            st_beginTransaction();
            session.update(project);
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
            Project project = session.get(Project.class, id);
            if (project != null) {
                session.delete(project);
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
