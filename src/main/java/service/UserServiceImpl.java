package service;


import dao.UserDao;
import dao.factoryImpl.UserDaoFactoryImpl;
import model.User;

import java.util.List;

public class UserServiceImpl implements UserServiсe {
    private UserDao userDao = UserDaoFactoryImpl.getDao();
    public UserServiceImpl() {

    }

    @Override
    public void addUser(User application) {
        userDao.addUser(application);
    }

    @Override
    public void deleteUser(int userId) {
        userDao.deleteUser(userId);
    }

    @Override
    public void updateUser(User application) {
        userDao.updateUser(application);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }
}
