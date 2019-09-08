package pl.wojtek120.school.controller.solution;

import pl.wojtek120.school.dao.ExerciseDao;
import pl.wojtek120.school.dao.SolutionDao;
import pl.wojtek120.school.dao.UserDao;
import pl.wojtek120.school.models.Exercise;
import pl.wojtek120.school.models.Solution;
import pl.wojtek120.school.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/solution")
public class ListAdminSolution extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SolutionDao solutionDao = new SolutionDao();
        ExerciseDao exerciseDao = new ExerciseDao();
        UserDao userDao = new UserDao();

        List<Solution> solutions = solutionDao.findAll();
        List<Exercise> exercises = new ArrayList<>();
        List<User> users = new ArrayList<>();

        for(Solution solution: solutions){
            exercises.add(exerciseDao.read(solution.getExerciseId()));
            users.add(userDao.read(solution.getUserId()));
        }


        request.setAttribute("solutions", solutions);
        request.setAttribute("exercises", exercises);
        request.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/web/solution/listAdminSolution.jsp").forward(request, response);
    }
}
