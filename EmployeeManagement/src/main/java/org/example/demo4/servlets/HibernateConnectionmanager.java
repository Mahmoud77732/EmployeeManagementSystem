package org.example.demo4.servlets;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateConnectionmanager {

    private static SessionFactory sessionFactory;

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

    public static Session st_getCurrentSession(){
        return st_getSessionFactory().getCurrentSession();
    }

    public static Session st_openSession(){
        return st_getSessionFactory().openSession();
    }

    public static void st_closeSession(){
        st_getSessionFactory().getCurrentSession().close();
    }

    public static void st_beginTransaction(){
        st_getCurrentSession().beginTransaction();
    }

    public static void st_commitTransaction(){
        st_getCurrentSession().getTransaction().commit();
    }

    public static void st_rollbackTransaction(){
        st_getCurrentSession().getTransaction().rollback();
    }

    public static boolean st_isActiveTransaction(){
        return st_getCurrentSession().getTransaction().isActive();
    }

    public static Transaction st_getTransaction(){
        return st_getCurrentSession().getTransaction();
    }
}
