package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.apache.log4j.Logger;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * User: artemk
 * Date: 8/2/14
 * Time: 9:02 PM
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(HibernateUtil.class);
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            logger.error("Initial SessionFactory creation failed. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
