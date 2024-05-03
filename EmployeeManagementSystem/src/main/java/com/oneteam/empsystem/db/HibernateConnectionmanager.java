package com.oneteam.empsystem.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateConnectionmanager {

    private static SessionFactory sessionFactory;
    private static ThreadLocal<Session> sessionThreadLocal = new ThreadLocal<>();

    public static SessionFactory st_getSessionFactory() {
        if(sessionFactory == null){
            st_buildSessionFactory();
        }
        return sessionFactory;
    }

    private static void st_buildSessionFactory() {
        StandardServiceRegistry registry = null;
        try {
            registry = new StandardServiceRegistryBuilder()
                    .configure() // configuration file loaded
                    .build(); // build registry with the config_file
            MetadataSources sources = new MetadataSources(registry);
            Metadata metadata = sources.getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        } catch (Exception ex) {
            if(registry != null){
                StandardServiceRegistryBuilder.destroy(registry);
            }
            System.err.println(ex.getMessage()); // don't do this in the real projects
        }
    }

    public static Session st_getSession(){
        Session session = sessionThreadLocal.get();
        if(session == null || !session.isOpen()){
            session = st_getSessionFactory().openSession();
            sessionThreadLocal.set(session);
        }
        return session;
    }

    public static void st_closeSession(){
        Session session = sessionThreadLocal.get();
        if(session != null && session.isOpen()){
            session.close();
            sessionThreadLocal.remove();
        }
    }

    public static void st_beginTransaction(){
        Transaction transaction = st_getSession().beginTransaction();
        sessionThreadLocal.set(st_getSession());
    }

    public static void st_commitTransaction(){
        Transaction transaction = st_getSession().getTransaction();
        if (transaction != null && transaction.isActive()) {
            transaction.commit();
        }
        st_closeSession();
    }

    public static void st_rollbackTransaction(){
        Transaction transaction = st_getSession().getTransaction();
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
        st_closeSession();
    }

}
