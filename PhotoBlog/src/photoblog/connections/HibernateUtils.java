package photoblog.connections;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import photoblog.entity.users.User;

/**
 *
 * @author Diana
 */
public class HibernateUtils {

    private static SessionFactory sessionFactory;
    private final static Logger logger = Logger.getLogger(HibernateUtils.class);
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

    public void createNewUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            int id = (Integer) session.save(user);
            tx.commit();
            logger.info("User saved, [id=" + id + "].");
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            logger.error("Error while creating new user", ex);
        } finally {
            session.close();
        }
    }

    public void stop() {
        sessionFactory.close();
        if (serviceRegistry != null) {
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
        }
    }

}
