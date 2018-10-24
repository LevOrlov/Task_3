package main.java.dao.factoryImpl;

import main.java.dao.UserDao;
import main.java.dao.UserDaoFactory;
import main.java.dao.daoImpl.UserDaoHibernateImpl;
import main.java.dao.daoImpl.UserDaoJDBCImpl;
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

public class UserDaoFactoryImpl implements UserDaoFactory {

    @Override
    public UserDao implDao(Object connectType) {
    if ( connectType instanceof Connection){
        return new UserDaoJDBCImpl();
    }
    return new UserDaoHibernateImpl();
    }
}
