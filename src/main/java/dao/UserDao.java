package main.java.dao;

import main.java.model.User;

import java.util.List;

public interface UserDao {
    public void addUser(User application);
    public void deleteUser(int userId);
    public void updateUser(User application);
    public List<User> getAllUsers();
    public User getUserById(int userId);
}
