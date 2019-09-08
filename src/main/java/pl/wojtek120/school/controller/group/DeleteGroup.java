package pl.wojtek120.school.controller.group;

import pl.wojtek120.school.dao.SolutionDao;
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

@WebServlet("/admin/group/delete")
public class DeleteGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserGroupDao userGroupDao = new UserGroupDao();
        UserDao userDao = new UserDao();

        int groupId = Integer.parseInt(request.getParameter("id"));


        for(User user : userDao.findAllByGroupId(groupId)){
            user.setUserGroupId(0);
            userDao.update(user);
        }

        userGroupDao.delete(groupId);

        response.sendRedirect("/admin/groups");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserGroupDao userGroupDao = new UserGroupDao();
        UserGroup userGroup = userGroupDao.read(id);

        request.setAttribute("name", userGroup.getName());
        request.setAttribute("id", id);
        request.setAttribute("delete", "admin/group");

        getServletContext().getRequestDispatcher("/web/general/deleteConfirmation.jsp").forward(request, response);
    }
}
