package main.java.servlet;

import main.java.model.User;
import main.java.service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/admin/delete")
public class ServletDelete extends HttpServlet {
    UserServiceImpl userDao = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action.equalsIgnoreCase("delete")) {
            int userId = Integer.parseInt(req.getParameter("id"));
            userDao.deleteUser(userId);
            String forward = "/admin.jsp";
            req.setAttribute("users", userDao.getAllUsers());
            req.getRequestDispatcher(forward).forward(req, resp);
        }
    }

}
