package main.java.dao.daoImpl;


import main.java.dao.UserDao;
import main.java.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory;
    private Session session;
    Transaction transObj;
//TODO доделать последние два метода в трае, но перед этим проверить как работает вообще с траем все остальные

    public UserDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User application) {
        try {
            session = sessionFactory.getCurrentSession();
            transObj = session.beginTransaction();
            int result = (Integer) session.save(application);
            transObj.commit();
        } catch (Exception e) {
            transObj.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteUser(int userId) {
        try {
            session = sessionFactory.getCurrentSession();
            transObj = session.beginTransaction();
            User user = (User) session.load(User.class, userId);
            session.delete(user);
            transObj.commit();
        } catch (Exception e) {
            transObj.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void updateUser(User application) {
        try {
            session = sessionFactory.getCurrentSession();
            transObj = session.beginTransaction();
            User user = application;
            session.saveOrUpdate(user);
            transObj.commit();
        } catch (Exception e) {
            transObj.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> empList = null;
        try {
            session = sessionFactory.getCurrentSession();
            transObj = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            empList = criteria.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            transObj.rollback();
        } finally {
            session.close();
        }
        return empList;
    }

    @Override
    public User getUserById(int userId) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User result = (User) session.load(User.class, userId);
        session.getTransaction().commit();
        return result;
    }

    public User getUserByLogin(String login) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User result = (User) session.createCriteria(User.class, login)
                .add(Restrictions.eq("login", login))
                .uniqueResult();

        session.getTransaction().commit();
        return result;
    }
}
