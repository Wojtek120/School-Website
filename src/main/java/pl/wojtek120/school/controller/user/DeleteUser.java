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

@WebServlet("/admin/user/delete")
public class DeleteUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        int userId = Integer.parseInt(request.getParameter("id"));

        userDao.delete(userId);

        response.sendRedirect("/admin/user");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserDao userDao = new UserDao();
        User user = userDao.read(id);

        request.setAttribute("name", user.getUsername());
        request.setAttribute("id", id);
        request.setAttribute("delete", "admin/user");

        getServletContext().getRequestDispatcher("/web/general/deleteConfirmation.jsp").forward(request, response);
    }
}
