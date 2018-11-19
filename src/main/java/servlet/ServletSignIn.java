package servlet;

import dao.daoImpl.UserDaoHibernateImpl;
import model.User;
import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/index")
public class ServletSignIn extends HttpServlet {

    UserServiceImpl userDao = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = userDao.getUserByLogin(req.getParameter("login"));
        String username = req.getParameter("login");
        String password = req.getParameter("password");

        String role = user.getRole();
        if (user.getLogin().equals(username) && user.getPassword().equals(password)) {
            req.getSession().setAttribute("user", user);
            if (role.equals("user")) {
                String forward = "/user";
                req.getSession().setAttribute("user", user);
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher(forward);
                rd.forward(req, resp);
            } else if (role.equals("admin")) {
                String forward = "/admin";
                req.setAttribute("users", userDao.getAllUsers());
                req.getSession().setAttribute("user", user);
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher(forward);
                rd.forward(req, resp);

            }
        }
    }

}
