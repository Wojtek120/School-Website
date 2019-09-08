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

@WebServlet("/admin/solution/add")
public class AddSolution extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SolutionDao solutionDao = new SolutionDao();

        int userId = Integer.parseInt(request.getParameter("chooseUser"));
        int exerciseId = Integer.parseInt(request.getParameter("chooseExercise"));
        String description = request.getParameter("description");

        Solution solution = new Solution(description, exerciseId, userId);
        solutionDao.create(solution);

        response.sendRedirect("/admin/solution");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExerciseDao exerciseDao = new ExerciseDao();
        UserDao userDao = new UserDao();

        request.setAttribute("exercises", exerciseDao.findAll());
        request.setAttribute("users", userDao.findAll());
        getServletContext().getRequestDispatcher("/web/solution/addSolution.jsp").forward(request, response);
    }
}
