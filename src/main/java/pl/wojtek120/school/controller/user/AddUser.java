package pl.wojtek120.school.controller.user;

import pl.wojtek120.school.dao.UserDao;
import pl.wojtek120.school.dao.UserGroupDao;
import pl.wojtek120.school.models.User;
import pl.wojtek120.school.models.UserGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/admin/user/add")
public class AddUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int groupName = Integer.parseInt(request.getParameter("groupId"));

        User user = new User(username, email, password, groupName);
        userDao.create(user);

        response.sendRedirect("/admin/panel");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserGroupDao userGroupDao = new UserGroupDao();
        request.setAttribute("groups", userGroupDao.findAll());

        getServletContext().getRequestDispatcher("/web/user/addUser.jsp").forward(request, response);
    }
}
