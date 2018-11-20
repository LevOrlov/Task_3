package dao.factoryImpl;

import dao.UserDao;
import dao.daoImpl.UserDaoHibernateImpl;
import dao.daoImpl.UserDaoJDBCImpl;


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
            return new UserDaoJDBCImpl();
        } else {
            return new UserDaoHibernateImpl();
        }


    }
}
