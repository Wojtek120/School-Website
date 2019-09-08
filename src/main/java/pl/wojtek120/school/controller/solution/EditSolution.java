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

@WebServlet("/admin/solution/edit")
public class EditSolution extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SolutionDao solutionDao = new SolutionDao();

        int solutionId = Integer.parseInt(request.getParameter("id"));
        int userId = Integer.parseInt(request.getParameter("chooseUser"));
        int exerciseId = Integer.parseInt(request.getParameter("chooseExercise"));
        String description = request.getParameter("description");

        Solution solution = solutionDao.read(solutionId);
        solution.setUserId(userId);
        solution.setExerciseId(exerciseId);
        solution.setDescription(description);
        solutionDao.update(solution);

        response.sendRedirect("/admin/solution");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExerciseDao exerciseDao = new ExerciseDao();
        UserDao userDao = new UserDao();

        SolutionDao solutionDao = new SolutionDao();
        int id = Integer.parseInt(request.getParameter("id"));
        Solution solution = solutionDao.read(id);

        request.setAttribute("solution", solution);
        request.setAttribute("exercises", exerciseDao.findAll());
        request.setAttribute("users", userDao.findAll());

        getServletContext().getRequestDispatcher("/web/solution/editSolution.jsp").forward(request, response);
    }
}
