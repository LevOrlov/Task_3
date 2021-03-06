package main.java.dao.daoImpl;


import main.java.dao.UserDao;
import main.java.model.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;
    PreparedStatement preparedStatement;

    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addUser(User application) {
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection
                    .prepareStatement("insert into table_name(name,login,password) values (?, ?, ?)");
            preparedStatement.setString(1, application.getName());
            preparedStatement.setString(2, application.getLogin());
            preparedStatement.setString(3, application.getPassword());
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteUser(int userId) {
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection
                    .prepareStatement("delete from table_name where id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateUser(User application) {
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection
                    .prepareStatement("update table_name set name=?, login=?, password=?" +
                            "where id=?");
            // Parameters start with 1
            preparedStatement.setString(1, application.getName());
            preparedStatement.setString(2, application.getLogin());

            preparedStatement.setString(3, application.getPassword());
            preparedStatement.setInt(4, application.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from table_name");
            while (rs.next()) {
                System.out.println("fffff");
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setLogin(rs.getString("login"));
                users.add(user);
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return users;

    }

    @Override
    public User getUserById(int userId) {
        User user = new User();
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.
                    prepareStatement("select * from table_name where id=?");
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setLogin(rs.getString("login"));
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = new User();
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.
                    prepareStatement("select * from table_name where login=?");
            preparedStatement.setString(1, login);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setLogin(rs.getString("login"));
                user.setRole(rs.getString("role"));
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }
}
