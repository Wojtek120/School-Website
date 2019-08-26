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
import java.util.List;

@WebServlet("/group/users")
public class listUsersByGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserGroupDao userGroupDao = new UserGroupDao();
        UserDao userDao = new UserDao();
        int groupId = Integer.parseInt(request.getParameter("id"));
        List<User> allByGroupId = userDao.findAllByGroupId(groupId);

        request.setAttribute("group", userGroupDao.read(groupId));
        request.setAttribute("users", userDao.findAllByGroupId(groupId));

        getServletContext().getRequestDispatcher("/web/user/listUser.jsp").forward(request, response);
    }
}
