package com.oneteam.empsystem.db;

import jakarta.servlet.*;

// i try to make the db connection faster
// initializes the Hibernate session factory when the application starts
// because my app is highly depend on db connection
public class HibernateInitializer implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        try {
            // Initialize Hibernate
            HibernateConnectionmanager.st_getSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Error initializing Hibernate: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void contextDestroyed(ServletContextEvent event) {
        // Shutdown Hibernate
        HibernateConnectionmanager.st_getSessionFactory().close();
    }
}

