package dao.daoImpl;

import dao.DBHelper;

import dao.UserDao;


import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    @Override
    public void addUser(User application) {
        Session session = DBHelper.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        int result = (Integer) session.save(application);
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
        Criteria criteria = session.createCriteria(User.class);
        List<User> empList = criteria.list();
        session.getTransaction().commit();
        List<? extends Number> numList = new ArrayList<Number>();

        return empList;

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
