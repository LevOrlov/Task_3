package servlet;


import dao.UserDao;
import dao.factoryImpl.UserDaoFactoryImpl;
import service.UserServiceImpl;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UserServlet")
public class ServletList extends HttpServlet {
    String forward;
    private static String LIST_USER = "/listUser.jsp";
    UserServiceImpl userDao = new UserServiceImpl();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userDao.getAllUsers());
        forward = LIST_USER;
        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
