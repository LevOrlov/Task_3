package main.java.dao;

import java.sql.Connection;

//абстрактная фабрика которая возвращает реализацию дао на основе файла property
//
public interface UserDaoFactory {
    UserDao implDao(Object connectType);



}
