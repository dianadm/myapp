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

    public User getUserById(int id) {
        Session session = sessionFactory.openSession();
        List<User> users = new ArrayList<>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("id", id));
            users = criteria.list();
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            logger.error("Error while creating new user", ex);
        } finally {
            session.close();
        }

        if (users.size()>1) {
            logger.error("Error, more than one user with id="+id);
            return null;
        } else {
            return users.get(0);
        }
    }

    public void stop() {
        sessionFactory.close();
        if (serviceRegistry != null) {
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
        }
    }

}
