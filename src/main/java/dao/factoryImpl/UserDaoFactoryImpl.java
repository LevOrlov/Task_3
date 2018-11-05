package dao.factoryImpl;

import dao.DBHelper;
import dao.UserDao;
import dao.daoImpl.UserDaoHibernateImpl;
import dao.daoImpl.UserDaoJDBCImpl;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class UserDaoFactoryImpl {
    static String dao;

    //TODO getDAO . я в нем должен ничего не принимать ничего. и там читать dao.properties. и возвращать реализацию
    public static UserDao getDao() {

        try {
            InputStream inputStream = UserDaoFactoryImpl.class.getClassLoader().getResourceAsStream("dao/dao.properties");
            Properties prop = new Properties();
            prop.load(inputStream);
            dao = prop.getProperty("dao");

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (dao.equals("jdbc")) {
            return new UserDaoJDBCImpl();
        } else return new UserDaoHibernateImpl();

    }
}
