package main.java;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.metamodel.Metadata;
import org.hibernate.metamodel.MetadataSources;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.imageio.spi.ServiceRegistry;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//синглтон, у него есть два метода getConnection и getConfiguration
// которые отдают Connection для jdbc dao и Configuration для hibernatedao соотвтетственно
public class DBHelper {
    private static Connection connection = null;
    private static SessionFactory sessionFactory = null;
    private static StandardServiceRegistry registry;
    private static DBHelper ourInstance = new DBHelper();

    public static DBHelper getInstance() {
        return ourInstance;
    }

    public static Connection getConnection() {
        {
            if (connection != null)
                return connection;
            else {
                try {
                    InputStream inputStream = new FileInputStream("main/resources/db.properties");
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
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
            Configuration cfg = new Configuration();
            cfg.setProperty("connection.driver_class", "com.mysql.jdbc.Driver");
            cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            cfg.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/task_3?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
            cfg.setProperty("hibernate.connection.username", "root");
            cfg.setProperty("hibernate.connection.password", "admin1");
            cfg.setProperty("hibernate.current_session_context_class", "thread");
            cfg.setProperty("hibernate.connection.autocommit", "thread");
            cfg.addAnnotatedClass(main.java.model.User.class);
            //вот атк не работает пишет ошибку о том, что нет аннотациий в классе энтити(не известный энтити)
            /*prop.setProperty("connection.driver_class", "com.mysql.jdbc.Driver");
            prop.setProperty("connection.driver_class", "com.mysql.jdbc.Driver");*/
           /*Map<String, String> settings = new HashMap<>();
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
            settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
            settings.put(Environment.USER, "root");
            settings.put(Environment.PASS, "admin1");
           // settings.put(Environment.)
            settings.put(Environment.URL, "jdbc:mysql://localhost:3306/task_3?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
            settings.put("hibernate.current_session_context_class", "thread");
            settings.put("hibernate.connection.autocommit", "false");
            settings.put("connection_pool_size", "1");
            settings.put("show_sql", "true");
            registryBuilder.applySettings(settings);
            registry = registryBuilder.build();
            MetadataSources sources = new MetadataSources(registry);
            Metadata metadata = sources.getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();*/
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(cfg.getProperties()).build();


            sessionFactory =  cfg.buildSessionFactory(serviceRegistry);

        } catch (Throwable ex) {

            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return sessionFactory;
    }

    public DBHelper() {
    }
}
