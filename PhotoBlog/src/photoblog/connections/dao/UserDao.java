package photoblog.connections.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import photoblog.connections.HibernateUtils;
import photoblog.entity.users.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {


    private HibernateUtils utils;
    private final static Logger logger = LogManager.getLogger(UserDao.class);

    @Autowired
    public UserDao(HibernateUtils utils) {
        this.utils = utils;
    }

    public void createNewUser(User user) {
        Session session = utils.getSessionFactory().openSession();
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
        Session session = utils.getSessionFactory().openSession();
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
}
