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

    private UserServiceImpl userDao = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userDao.getAllUsers());
        RequestDispatcher view = req.getRequestDispatcher("/listUser.jsp");
        view.forward(req, resp);
    }
}
