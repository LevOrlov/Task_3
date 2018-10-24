package main.java.dao.daoImpl;

import main.java.DBHelper;
import main.java.dao.UserDao;
import main.java.dao.UserDaoFactory;
import main.java.dao.factoryImpl.UserDaoFactoryImpl;
import main.java.model.User;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    @Override
    public void addUser(User application) {
        Session session = DBHelper.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Integer result = (Integer) session.save(application);
        session.getTransaction().commit();
    }

    @Override
    public void deleteUser(int userId) {
        Session session = DBHelper.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User user = (User) session.load(User.class, userId);
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public void updateUser(User application) {
        Session session = DBHelper.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User user = application;
        session.saveOrUpdate(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = DBHelper.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<User> list = session.createCriteria(User.class).list();
        session.getTransaction().commit();
        return list;
    }

    @Override
    public User getUserById(int userId) {
        Session session = DBHelper.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User result = (User) session.load(User.class, userId);
        session.getTransaction().commit();
        return result;
    }
}
