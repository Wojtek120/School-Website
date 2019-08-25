package pl.wojtek120.school.controller.solution;

import pl.wojtek120.school.dao.ExerciseDao;
import pl.wojtek120.school.dao.SolutionDao;
import pl.wojtek120.school.dao.UserDao;
import pl.wojtek120.school.models.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/solution/details")
public class DetailsSolution extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SolutionDao solutionDao = new SolutionDao();
        ExerciseDao exerciseDao = new ExerciseDao();
        UserDao userDao = new UserDao();

        int id = Integer.parseInt(request.getParameter("id"));

        Solution solution = solutionDao.read(id);

        request.setAttribute("solution", solution);
        request.setAttribute("exercise", exerciseDao.read(solution.getExerciseId()));
        request.setAttribute("user", userDao.read(solution.getUserId()));

        getServletContext().getRequestDispatcher("/web/solution/detailsSolution.jsp").forward(request, response);
    }
}
