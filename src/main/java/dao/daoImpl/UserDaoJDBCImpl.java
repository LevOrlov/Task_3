package main.java.dao.daoImpl;

import main.java.DBHelper;
import main.java.dao.UserDao;
import main.java.dao.factoryImpl.UserDaoFactoryImpl;
import main.java.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    @Override
    public void addUser(User application) {
        try {
            PreparedStatement preparedStatement = DBHelper.getConnection()
                    .prepareStatement("insert into apl(name,login,password) values (?, ?, ?)");
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
                    .prepareStatement("delete from apl where id=?");
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
                    .prepareStatement("update apl set name=?, login=?, password=?" +
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
            ResultSet rs = statement.executeQuery("select * from apl");
            while (rs.next()) {
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
                    prepareStatement("select * from apl where id=?");
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
}
