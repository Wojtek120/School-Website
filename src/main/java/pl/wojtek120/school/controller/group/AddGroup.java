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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/group/add")
public class AddGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserGroupDao userGroupDao = new UserGroupDao();
        UserDao userDao = new UserDao();

        String groupName = request.getParameter("GroupName");
        UserGroup group = userGroupDao.create(new UserGroup(groupName));

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
        request.setAttribute("users", userDao.findAll());

        getServletContext().getRequestDispatcher("/web/group/addGroup.jsp").forward(request, response);
    }
}
