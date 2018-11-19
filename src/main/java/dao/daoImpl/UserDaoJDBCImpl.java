package dao.daoImpl;

import dao.DBHelper;

import dao.UserDao;
import model.User;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    //TODO общую конектион на все методы
    @Override
    public void addUser(User application) {
        try {
            PreparedStatement preparedStatement = DBHelper.getConnection()
                    .prepareStatement("insert into table_name(name,login,password) values (?, ?, ?)");
            preparedStatement.setString(1, application.getName());
            preparedStatement.setString(2, application.getLogin());
            preparedStatement.setString(3, application.getPassword());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int userId) {
        try {
            PreparedStatement preparedStatement = DBHelper.getConnection()
                    .prepareStatement("delete from table_name where id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User application) {
        try {
            PreparedStatement preparedStatement = DBHelper.getConnection()
                    .prepareStatement("update table_name set name=?, login=?, password=?" +
                            "where id=?");
            // Parameters start with 1
            preparedStatement.setString(1, application.getName());
            preparedStatement.setString(2, application.getLogin());

            preparedStatement.setString(3, application.getPassword());
            preparedStatement.setInt(4, application.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try {
            Statement statement = DBHelper.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("select * from table_name");
            while (rs.next()) {
                System.out.println("fffff");
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setLogin(rs.getString("login"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;

    }

    @Override
    public User getUserById(int userId) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = DBHelper.getConnection().
                    prepareStatement("select * from table_name where id=?");
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setLogin(rs.getString("login"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    @Override
    public User getUserByLogin(String login) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = DBHelper.getConnection().
                    prepareStatement("select * from table_name where login=?");
            preparedStatement.setString(1, login);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setLogin(rs.getString("login"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
