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

@WebServlet("/admin/user/edit")
public class EditUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();

        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDao.read(id);

        user.setUsername(request.getParameter("username"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setUserGroupId(Integer.parseInt(request.getParameter("groupId")));

        userDao.update(user);

        response.sendRedirect("/admin/user");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        UserGroupDao userGroupDao = new UserGroupDao();

        int id = Integer.parseInt(request.getParameter("id"));

        request.setAttribute("user", userDao.read(id));
        request.setAttribute("groups", userGroupDao.findAll());

        getServletContext().getRequestDispatcher("/web/user/editUser.jsp").forward(request, response);
    }
}
