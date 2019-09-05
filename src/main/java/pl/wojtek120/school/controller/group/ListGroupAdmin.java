package pl.wojtek120.school.controller.group;

import pl.wojtek120.school.dao.UserGroupDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/groups")
public class ListGroupAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserGroupDao userGroupDao = new UserGroupDao();
        request.setAttribute("groups", userGroupDao.findAll());

        getServletContext().getRequestDispatcher("/web/group/listGroupAdmin.jsp").forward(request, response);
    }
}
