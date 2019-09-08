package pl.wojtek120.school.controller.user;

import pl.wojtek120.school.dao.UserDao;
import pl.wojtek120.school.dao.UserGroupDao;
import pl.wojtek120.school.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/user")
public class ListUserAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserGroupDao userGroupDao = new UserGroupDao();
        UserDao userDao = new UserDao();

        request.setAttribute("userGroupDao", userGroupDao);
        request.setAttribute("users", userDao.findAll());

        getServletContext().getRequestDispatcher("/web/user/listUserAdmin.jsp").forward(request, response);
    }
}
