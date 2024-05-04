package com.oneteam.empsystem.repo.reposimpl;
import com.oneteam.empsystem.repo.repos.GenericRepo;
import org.hibernate.Session;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

import static com.oneteam.empsystem.db.HibernateConnectionmanager.*;

public class GenericRepoImpl<T, PK extends Serializable> implements GenericRepo<T, PK> {

    private final Class<T> entityType;

    public GenericRepoImpl(Class<T> entityType) {
        this.entityType = entityType;
    }

    @Override
    public void save(T entity) {
        try (Session session = st_getSession()) {
            st_beginTransaction();
            session.save(entity);
            st_commitTransaction();
        } catch (Exception ex) {
            if (st_getSession().getTransaction().isActive()) {
                st_rollbackTransaction();
            }
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public T findById(PK id) {
        try (Session session = st_getSession()) {
            return session.get(entityType, id);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public void update(T entity) {
        try (Session session = st_getSession()) {
            st_beginTransaction();
            session.update(entity);
            st_commitTransaction();
        } catch (Exception ex) {
            if (st_getSession().getTransaction().isActive()) {
                st_rollbackTransaction();
            }
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void remove(T entity) {
        try (Session session = st_getSession()) {
            st_beginTransaction();
            session.delete(entity);
            st_commitTransaction();
        } catch (Exception ex) {
            if (st_getSession().getTransaction().isActive()) {
            st_rollbackTransaction();
            }
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<T> findAll() {
        try (Session session = st_getSession()) {
            return session.createQuery("FROM " + entityType.getName(), entityType).list();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

}
