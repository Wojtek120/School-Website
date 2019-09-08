package pl.wojtek120.school.controller.group;

import pl.wojtek120.school.dao.ExerciseDao;
import pl.wojtek120.school.dao.SolutionDao;
import pl.wojtek120.school.dao.UserDao;
import pl.wojtek120.school.dao.UserGroupDao;
import pl.wojtek120.school.models.Solution;
import pl.wojtek120.school.models.User;
import pl.wojtek120.school.models.UserGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/admin/group/edit")
public class EditGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserGroupDao userGroupDao = new UserGroupDao();
        UserDao userDao = new UserDao();

        String groupName = request.getParameter("GroupName");
        int groupId = Integer.parseInt(request.getParameter("id"));

        for(User user : userDao.findAllByGroupId(groupId)){
            user.setUserGroupId(0);
            userDao.update(user);
        }

        UserGroup group = userGroupDao.read(groupId);
        group.setName(groupName);
        userGroupDao.update(group);

        String[] userIdStr = request.getParameterValues("chooseUser");
        int[] userId = Arrays.stream(userIdStr).mapToInt(Integer::parseInt).toArray();

        for (int id : userId) {
            User user = userDao.read(id);
            user.setUserGroupId(group.getId());
            userDao.update(user);
        }

        response.sendRedirect("/admin/panel");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        UserGroupDao userGroupDao = new UserGroupDao();

        int id = Integer.parseInt(request.getParameter("id"));

        request.setAttribute("users", userDao.findAll());
        request.setAttribute("group", userGroupDao.read(id));

        getServletContext().getRequestDispatcher("/web/group/editGroup.jsp").forward(request, response);
    }
}
