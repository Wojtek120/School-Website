package pl.wojtek120.school.controller.user;

import pl.wojtek120.school.dao.ExerciseDao;
import pl.wojtek120.school.dao.SolutionDao;
import pl.wojtek120.school.dao.UserDao;
import pl.wojtek120.school.dao.UserGroupDao;
import pl.wojtek120.school.models.Exercise;
import pl.wojtek120.school.models.Solution;
import pl.wojtek120.school.models.User;
import pl.wojtek120.school.models.UserGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users/details")
public class DetailsUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        UserGroupDao userGroupDao = new UserGroupDao();
        SolutionDao solutionDao = new SolutionDao();
        ExerciseDao exerciseDao = new ExerciseDao();

        int userId = Integer.parseInt(request.getParameter("userId"));
        int groupId = Integer.parseInt(request.getParameter("groupId"));

        List<Solution> solutions = solutionDao.findAllByUserId(userId);
        List<Exercise> exercises = new ArrayList<>();

        for(Solution solution: solutions){
            exercises.add(exerciseDao.read(solution.getExerciseId()));
        }

        request.setAttribute("user", userDao.read(userId));
        request.setAttribute("group", userGroupDao.read(groupId));
        request.setAttribute("solutions", solutions);
        request.setAttribute("exercises", exercises);

        getServletContext().getRequestDispatcher("/web/user/detailsUser.jsp").forward(request, response);
    }
}
