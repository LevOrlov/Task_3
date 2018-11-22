package main.java.dao.factoryImpl;

import main.java.dao.DBHelper;
import main.java.dao.UserDao;
import main.java.dao.daoImpl.UserDaoHibernateImpl;
import main.java.dao.daoImpl.UserDaoJDBCImpl;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactoryImpl {



    public static UserDao getDao() {
        String dao = "";
        try {
            InputStream inputStream = UserDaoFactoryImpl.class.getClassLoader().getResourceAsStream("config.properties");
            Properties prop = new Properties();
            prop.load(inputStream);
            dao = prop.getProperty("dao");

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (dao.equals("jdbc")) {
            return new UserDaoJDBCImpl(DBHelper.getConnection());
        } else {
            return new UserDaoHibernateImpl(DBHelper.getSessionFactory());
        }


    }
}
