package servlet;


import dao.UserDao;
import dao.factoryImpl.UserDaoFactoryImpl;
import model.User;
import service.UserServiceImpl;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/add")
public class ServletAdd extends HttpServlet {
    private static String LIST_USER = "/listUser.jsp";
    UserServiceImpl userDao = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward;
        req.setAttribute("users", userDao.getAllUsers());
        forward = "/add.jsp";
        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equalsIgnoreCase("add")){
            User user = new User();
            user.setName(req.getParameter("name"));
            user.setLogin(req.getParameter("login"));
            user.setPassword(req.getParameter("password"));
            userDao.addUser(user);

            String forward=LIST_USER;
            req.setAttribute("users", userDao.getAllUsers());
            RequestDispatcher view = req.getRequestDispatcher(forward);
            view.forward(req, resp);

        }
    }
}
