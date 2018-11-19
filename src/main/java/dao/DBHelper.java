package dao;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//синглтон, у него есть два метода getConnection и getConfiguration
// которые отдают Connection для jdbc dao и Configuration для hibernatedao соотвтетственно
public class DBHelper {
    private static Connection connection = null;
    private static SessionFactory sessionFactory = null;
    private static StandardServiceRegistry registry;
    private static DBHelper ourInstance = new DBHelper();

    private DBHelper() {
    }

    public static DBHelper getInstance() {
        return ourInstance;
    }

    public static Connection getConnection() {
        {
            if (connection != null)
                return connection;
            else {
                try {
                    InputStream inputStream = DBHelper.class.getClassLoader().getResourceAsStream("config.properties");
                    Properties prop = new Properties();
                    prop.load(inputStream);
                    String driver = prop.getProperty("driver");
                    String url = prop.getProperty("url");
                    String user = prop.getProperty("user");
                    String password = prop.getProperty("password");
                    Class.forName(driver);
                    connection = DriverManager.getConnection(url, user, password);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return connection;
            }

        }
    }

    public static SessionFactory getSessionFactory() {
        {
            if (sessionFactory != null)
                return sessionFactory;
            else {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Configuration cfg = new Configuration();
                    InputStream inputStream = DBHelper.class.getClassLoader().getResourceAsStream("config.properties");
                    Properties prop = new Properties();
                    prop.load(inputStream);
                    String driver = prop.getProperty("driver");
                    String url = prop.getProperty("url");
                    String user = prop.getProperty("user");
                    String password = prop.getProperty("password");
                    String dialect = prop.getProperty("dialect");
                    cfg.setProperty("connection.driver_class", driver);
                    cfg.setProperty("hibernate.dialect", dialect);
                    cfg.setProperty("hibernate.connection.url", url);
                    cfg.setProperty("hibernate.connection.username", user);
                    cfg.setProperty("hibernate.connection.password", password);
                    cfg.setProperty("hibernate.current_session_context_class", "thread");
                    cfg.setProperty("hibernate.connection.autocommit", "thread");
                    cfg.addAnnotatedClass(User.class);
                    StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                            .applySettings(cfg.getProperties()).build();
                    sessionFactory = cfg.buildSessionFactory(serviceRegistry);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return sessionFactory;

            }
        }
    }
}
