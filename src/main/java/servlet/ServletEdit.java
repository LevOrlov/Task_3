package main.java.servlet;

import main.java.DBHelper;
import main.java.dao.factoryImpl.UserDaoFactoryImpl;
import main.java.model.User;
import org.hibernate.SessionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/edit")
public class ServletEdit extends HttpServlet {
    private static String LIST_USER = "/listUser.jsp";
    private UserDaoFactoryImpl dao =new UserDaoFactoryImpl();
    private Connection connection= DBHelper.getConnection();
    //private SessionFactory connection= DBHelper.getSessionFactory();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward;
        req.setAttribute("user", dao.implDao(connection).getUserById(Integer.parseInt(req.getParameter("id"))));
        forward = "/edit.jsp";
        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equalsIgnoreCase("edit")){
            User user = new User();
            user.setId(Integer.parseInt(req.getParameter("id")));
            user.setName(req.getParameter("name"));
            user.setLogin(req.getParameter("login"));
            user.setPassword(req.getParameter("password"));
            dao.implDao(connection).updateUser(user);
            String forward=LIST_USER;

            req.setAttribute("users", dao.implDao(connection).getAllUsers());
            RequestDispatcher view = req.getRequestDispatcher(forward);
            view.forward(req, resp);

        }
    }
}