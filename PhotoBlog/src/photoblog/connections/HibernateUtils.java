package photoblog.connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import photoblog.entity.users.User;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diana
 */
public class HibernateUtils {

    private static SessionFactory sessionFactory;
    // log4j2
    private final static Logger logger = LogManager.getLogger(HibernateUtils.class);
    private StandardServiceRegistry serviceRegistry;

    public HibernateUtils() {
        try {
            if (sessionFactory == null) {
                Configuration configuration = new Configuration();
                configuration.addAnnotatedClass(User.class).configure();
                serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                        configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }
        } catch (HibernateException ex) {
            logger.error("Error during initialization", ex);
            throw new ExceptionInInitializerError(ex);
        }

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void stop() {
        sessionFactory.close();
        if (serviceRegistry != null) {
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
        }
    }

}
