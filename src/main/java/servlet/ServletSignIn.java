package servlet;

import dao.daoImpl.UserDaoHibernateImpl;
import model.User;
import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index")
public class ServletSignIn extends HttpServlet {

    UserServiceImpl userDao = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userDao.getUserByLogin(req.getParameter("login"));

        String action = user.getRole();
        if (action.equals("user")){
            String forward="/user.jsp";
            req.setAttribute("users", userDao.getAllUsers());
            RequestDispatcher view = req.getRequestDispatcher(forward);
            view.forward(req, resp);
        }
        else if (action.equals("admin")){
            String forward="/listUser.jsp";
            req.setAttribute("users", userDao.getAllUsers());
            RequestDispatcher view = req.getRequestDispatcher(forward);
            view.forward(req, resp);
        }
    }
}
